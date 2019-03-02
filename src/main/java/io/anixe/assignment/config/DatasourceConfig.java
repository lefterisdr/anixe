package io.anixe.assignment.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by l.dritsas on 27/2/2019.
 */
@Configuration
public class DatasourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "hotel.datasource")
    @Profile("!test")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
