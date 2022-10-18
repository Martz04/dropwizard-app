package com.mario;

import com.mario.bundles.HelloWorldJdbiBundle;
import com.mario.configuration.DatabaseConfiguration;
import com.mario.health.HelloWorldHealthCheck;
import com.mario.resource.HelloResourceImpl;
import com.mario.resource.UserResourceImpl;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception{
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // Add bundles
        bootstrap.addBundle(databaseBundle());

        // Necessary bundle to replace env variables in server.yml
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(true)
        ));
    }

    protected HelloWorldJdbiBundle<HelloWorldConfiguration> databaseBundle() {
        return new HelloWorldJdbiBundle<>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration config) {
                return config.getDatabaseConfiguration();
            }
        };
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        log.info("Registering Resources");
        environment.jersey().register(new HelloResourceImpl());
        environment.jersey().register(UserResourceImpl.class);

        environment.healthChecks().register("BasicDropWizardHealthCheck", new HelloWorldHealthCheck(configuration));
    }
}
