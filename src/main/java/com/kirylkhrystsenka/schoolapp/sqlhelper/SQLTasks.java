package com.kirylkhrystsenka.schoolapp.sqlhelper;

import com.kirylkhrystsenka.schoolapp.dao.utilities.DBConfiguration;
import com.kirylkhrystsenka.schoolapp.dao.utilities.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class SQLTasks {
    private DBUtil dbUtil = null;

    public SQLTasks(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfiguration.class);
        dbUtil = context.getBean("dbUtil", DBUtil.class);
    }

    public void createDatabase() {
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\mikas\\IdeaProjects\\school-console-app\\src\\main\\resources\\drop_db.sql"));
             Connection connection = dbUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\mikas\\IdeaProjects\\school-console-app\\src\\main\\resources\\init_scheme.sql"));
             Connection connection = dbUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\mikas\\IdeaProjects\\school-console-app\\src\\main\\resources\\generate_data.sql"));
             Connection connection = dbUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public String findAllGroupsWithLessOrEqualStudentsNumber(int numberOfStudents) {
        String query = """
                SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count
                                                                          FROM groups
                                                                          LEFT JOIN students ON groups.group_id = students.group_id
                                                                          GROUP BY groups.group_id, groups.group_name
                                                                          HAVING COUNT(students.student_id) <= ?
                                                      					ORDER BY groups.group_id;
                """;
        StringBuilder result = new StringBuilder();
        try (Connection connection = dbUtil.getDataSource().getConnection();
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
        return result.toString().trim();
    }

    public String findAllStudentsRelatedToTheCourse(String groupName) {
        String query = """
                SELECT s.first_name, s.last_name, g.group_name
                                       FROM students AS s
                                       JOIN groups AS g
                                       ON s.group_id = g.group_id
                                       WHERE g.group_name LIKE ?
                """;
        StringBuilder result = new StringBuilder();
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.append(resultSet.getString("first_name")).append(" - ");
                result.append(resultSet.getString("second_name")).append(" - ");
                result.append(resultSet.getString("group_name")).append(System.lineSeparator());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.length() == 0 ? "There is no such group in database" : result.toString().trim();
    }

    public int addStudent(int groupID, String studentName, String studentSurname) {
        String query = """
                INSERT INTO students(group_id, first_name, last_name)
                VALUES (?,?,?)
                """;
        int result = 0;
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupID);
            statement.setString(2, studentName);
            statement.setString(3, studentSurname);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteStudent(int studentId) {
        String query = """
                DELETE FROM students WHERE student_id = ?;
                """;
        int result = 0;
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int addStudentToTheCourse(int studentId, int courseId) {
        String query = """
                INSERT INTO student_courses (student_id, course_id)
                VALUES ({student_id}, {course_id});
                """;
        int result = 0;
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int removeStudentFromTheCourse(int studentId, int courseId) {
        String query = """
                DELETE FROM student_courses WHERE student_id = ? AND course_id = ?;
                """;
        int result = 0;
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

