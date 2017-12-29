package com.project.java.prz.auth.configuration.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Piotr on 04.06.2017.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.user")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("oauth2Datasource")
    @ConfigurationProperties(prefix="datasource.oauth2")
    public DataSource oauth2Datasource() {
        return DataSourceBuilder.create().build();
    }

}
