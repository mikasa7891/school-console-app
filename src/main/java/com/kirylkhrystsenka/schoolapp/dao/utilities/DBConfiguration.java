package com.kirylkhrystsenka.schoolapp.dao.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {
    @Bean
    public DBUtil dbUtil(@Value("${db.driver}")String dbDriver,
                         @Value("${db.url}") String dbUrl,
                         @Value("${db.login}") String dbLogin,
                         @Value("${db.password}") String dbPassword) {
        return new DBUtil(dbDriver, dbUrl, dbLogin, dbPassword);
    }
}
