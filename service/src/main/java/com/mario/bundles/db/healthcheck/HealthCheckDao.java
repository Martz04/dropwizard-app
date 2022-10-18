package com.mario.bundles.db.healthcheck;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface HealthCheckDao {

    @SqlQuery("SELECT 1 FROM DUAL")
    Integer ping();
}
