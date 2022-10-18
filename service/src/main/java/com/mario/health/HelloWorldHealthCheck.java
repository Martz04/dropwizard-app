package com.mario.health;

import com.codahale.metrics.health.HealthCheck;
import com.mario.HelloWorldConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldHealthCheck extends HealthCheck {
    private static String appName;

    public HelloWorldHealthCheck(HelloWorldConfiguration configuration) {
        this.appName = configuration.getAppName();
    }

    @Override
    protected Result check() throws Exception {
        log.info("App name is {}", appName);
        if(appName.equalsIgnoreCase("Mario-Services")) {
            return Result.healthy();
        }
        return Result.unhealthy("App is down");
    }
}
