package com.example.config;

import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DataSource dataSource() throws UnknownHostException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://xxxxxxxxxx:xxxxx/xxxxxx?autoReconnect=true&useSSL=false");
        dataSource.setUsername("xxxx");
        dataSource.setPassword("xxxxxx");
        return dataSource;
    }

}
