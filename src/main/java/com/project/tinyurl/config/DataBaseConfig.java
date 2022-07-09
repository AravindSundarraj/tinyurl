package com.project.tinyurl.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.Jedis;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Value("${tinyurl.redis.conn}")
    private String redisConn;


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

  /*  @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis(redisConn);
        jedis.auth("","");
        return jedis;
    }*/


}
