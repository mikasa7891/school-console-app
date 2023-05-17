package com.kirylkhrystsenka.schoolapp.utilities;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBUtils {
    public static HikariDataSource createDatasource(Properties properties) {
        HikariDataSource dataSource = null;

        try {
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(properties.getBDUrl());
            dataSource.setUsername(properties.getDbUsername());
            dataSource.setPassword(properties.getDbPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
