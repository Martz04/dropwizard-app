package com.mario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mario.configuration.DatabaseConfiguration;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldConfiguration extends Configuration {

    @JsonProperty
    public String appName;

    @JsonProperty("db")
    public DatabaseConfiguration databaseConfiguration;
}
