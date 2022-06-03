package com.project.tinyurl.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {


    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog/changelog-master.yaml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    @ConfigurationProperties(prefix = "tinyurl.db")
    public DataSource dataSource(){

        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


}
