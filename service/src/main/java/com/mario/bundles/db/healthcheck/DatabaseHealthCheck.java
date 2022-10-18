package com.mario.bundles.db.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DatabaseHealthCheck extends HealthCheck {

    private final HealthCheckDao healthCheckDao;

    @Override
    protected Result check() {
        try {
            healthCheckDao.ping();
        } catch (Exception e) {
            log.warn("Unsuccessful database check. message={}", e.getMessage());
            return Result.unhealthy("Can't ping database");
        }
        return Result.healthy();
    }
}
