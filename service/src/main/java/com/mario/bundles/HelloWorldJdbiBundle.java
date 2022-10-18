package com.mario.bundles;

import com.mario.bundles.db.healthcheck.DatabaseHealthCheck;
import com.mario.bundles.db.healthcheck.HealthCheckDao;
import com.mario.bundles.db.user.UserDao;
import com.mario.configuration.DatabaseConfiguration;
import com.mario.service.UserService;
import com.mario.service.UserServiceImpl;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

@Slf4j
public abstract class HelloWorldJdbiBundle<T extends Configuration> implements ConfiguredBundle<T> {

    public abstract DatabaseConfiguration getDatabaseConfiguration(T config);

    @Override
    public void run(T config, Environment environment) throws Exception {
        var dbConfiguration = getDatabaseConfiguration(config);

        migrateDatabase(dbConfiguration, environment);
        var jdbi = new JdbiFactory()
                .build(environment, dbConfiguration.getDataSourceFactory(), "hello-world-db-jdbi");

        var healthCheckDao = jdbi.onDemand(HealthCheckDao.class);
        var userDao = jdbi.onDemand(UserDao.class);

        environment.healthChecks().register("db-health", new DatabaseHealthCheck(healthCheckDao));
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new UserServiceImpl(userDao)).to(UserService.class);
            }
        });
    }

    private void migrateDatabase(DatabaseConfiguration config, Environment environment) throws Exception{
        log.info("Running schema migration");
        var dataSourceFactory = config.getDataSourceFactory();
        var managedDataSource = dataSourceFactory.build(environment.metrics(), "migration-db-" + config.getName());

        try (var connection = managedDataSource.getConnection()) {
            JdbcConnection conn = new JdbcConnection(connection);
            Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(conn);
            try (Liquibase lb = new Liquibase(config.getMigrationFile(), new ClassLoaderResourceAccessor(), db)) {
                lb.update("");
                log.info("Migration Complete");
            } catch (Exception e) {
                log.warn(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
