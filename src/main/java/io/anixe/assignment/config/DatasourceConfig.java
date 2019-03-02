package io.anixe.assignment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "hotel.datasource")
    @Profile("!test")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
