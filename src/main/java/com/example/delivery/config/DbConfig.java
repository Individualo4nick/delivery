package com.example.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan
public class DbConfig {
    @Bean
    public JdbcTemplate getTemplate(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/delivery_mirea");
        ds.setUsername("root");
        ds.setPassword("wtf123");
        return new JdbcTemplate(ds);
    }
}