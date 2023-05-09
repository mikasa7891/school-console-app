package com.kirylkhrystsenka.schoolapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DaoFactory{
    INSTANCE();

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "T3n$hi7892";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load PostgreSQL JDBC driver.");
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }
}
