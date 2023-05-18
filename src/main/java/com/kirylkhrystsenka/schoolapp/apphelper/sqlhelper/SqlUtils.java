package com.kirylkhrystsenka.schoolapp.apphelper.sqlhelper;

import com.kirylkhrystsenka.schoolapp.dao.entities.Group;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
    private SqlUtils(){

    }
    public static int executeSqlScript(Connection connection, String filePath) throws SQLException, IOException {
        int result;
        try (InputStream inputStream = SqlUtils.class.getClassLoader().getResourceAsStream(filePath);
             Statement statement = connection.createStatement()) {
            assert inputStream != null:"File is not found";
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
                result = statement.executeUpdate(stringBuilder.toString());
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (IOException e) {
            throw new IOException(e);
        }
        return result;
    }
    public static <T> T withTransaction(HikariDataSource dataSource, UnsafeFunction<Connection, T> function) throws SQLException {
        T result = null;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                result = function.apply(connection);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return result;
    }

}
