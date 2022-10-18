package com.mario;

import com.mario.bundles.HelloWorldJdbiBundle;
import com.mario.configuration.DatabaseConfiguration;
import io.dropwizard.setup.Environment;

public class HelloWorldTestApplication extends HelloWorldApplication{

    @Override
    protected HelloWorldJdbiBundle<HelloWorldConfiguration> databaseBundle() {
        return new HelloWorldJdbiBundle<HelloWorldConfiguration>() {

            @Override
            public void run(HelloWorldConfiguration config, Environment environment) throws Exception {
                // Override DB connection with stub

            }

            @Override
            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration config) {
                return config.getDatabaseConfiguration();
            }
        };
    }
}
