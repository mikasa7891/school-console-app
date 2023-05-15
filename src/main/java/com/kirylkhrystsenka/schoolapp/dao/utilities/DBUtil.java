package com.kirylkhrystsenka.schoolapp.dao.utilities;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("dbUtil")
@Scope("singleton")
@PropertySource("classpath:db.properties")
public class DBUtil {
    private String dbDriver;
    private String dbUrl;
    private String dbLogin;
    private String dbPassword;

    private HikariDataSource dataSource;


    @Autowired
    public DBUtil(@Value("${db.driver}")String dbDriver,
                  @Value("${db.url}") String dbUrl,
                  @Value("${db.login}") String dbLogin,
                  @Value("${db.password}") String dbPassword) {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbLogin = dbLogin;
        this.dbPassword = dbPassword;
        initializeDataSource();
    }

    private void initializeDataSource() {
        try {
            dataSource = new HikariDataSource();
            dataSource.setDriverClassName(dbDriver);
            dataSource.setJdbcUrl(dbUrl);
            dataSource.setUsername(dbLogin);
            dataSource.setPassword(dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
