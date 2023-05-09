package com.kirylkhrystsenka.schoolapp.sqlhelper;

import com.kirylkhrystsenka.schoolapp.dao.DaoFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class SQLTasks {
    public void createDatabase() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        DaoFactory daoFactory = DaoFactory.getInstance();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/SQLTablesCreation"));
             Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public String findAllGroupsWithLessOrEqualStudentsNumber(int numberOfStudents) throws SQLException {
        String query = """
                SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count
                                                                          FROM groups
                                                                          LEFT JOIN students ON groups.group_id = students.group_id
                                                                          GROUP BY groups.group_id, groups.group_name
                                                                          HAVING COUNT(students.student_id) <= ?
                                                      					ORDER BY groups.group_id;
                """;
        StringBuilder result = new StringBuilder();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numberOfStudents);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.append(resultSet.getInt("group_id")).append(" - ");
                result.append(resultSet.getString("group_name")).append(" - ");
                result.append(resultSet.getInt("student_count")).append(System.lineSeparator());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.length() == 0) return "No such group in database";
        return result.toString().trim();
    }

    public String findAllStudentsRelatedToTheCourse(String groupName) throws SQLException {
        String query = """
                SELECT s.first_name, s.last_name, g.group_name
                                       FROM students AS s
                                       JOIN groups AS g\s
                                       ON s.group_id = g.group_id
                                       WHERE g.group_name LIKE ?
                """;
        StringBuilder result = new StringBuilder();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.append(resultSet.getString("first_name")).append(" - ");
                result.append(resultSet.getString("second_name")).append(" - ");
                result.append(resultSet.getString("group_name")).append(System.lineSeparator());
            }
            return result.toString().trim();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addStudent(int groupID, String studentName, String studentSurname) {
        String query = """
                INSERT INTO students(group_id, first_name, last_name)
                VALUES (?,?,?)
                """;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, groupID);
            statement.setString(2, studentName);
            statement.setString(3, studentSurname);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

