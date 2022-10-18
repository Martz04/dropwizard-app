package com.mario.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DatabaseConfiguration extends Configuration {

    @NotNull
    private String name = "db";

    @NotNull
    private String migrationFile = "migrations.xml";

    @NotNull
    @JsonProperty("connection")
    private DataSourceFactory dataSourceFactory;

    @NotNull
    private boolean migrateSchemaOnStartUp = true;
}
