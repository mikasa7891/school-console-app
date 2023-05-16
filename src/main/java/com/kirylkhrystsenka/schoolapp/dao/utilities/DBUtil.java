package com.kirylkhrystsenka.schoolapp.dao.utilities;

import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileReader;

public class DBUtil {
    private HikariDataSource dataSource;

    public DBUtil() {
        initializeDataSource();
    }

    private void initializeDataSource() {
        Properties properties = null;
        try {
            properties = new Properties(new BufferedReader(new FileReader("C:\\Users\\mikas\\IdeaProjects\\school-console-app\\src\\main\\resources\\db.properties")));
            properties.setProperties();

            dataSource = new HikariDataSource();

            dataSource.setJdbcUrl(properties.getBDUrl());
            dataSource.setUsername(properties.getDbUsername());
            dataSource.setPassword(properties.getDbPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
