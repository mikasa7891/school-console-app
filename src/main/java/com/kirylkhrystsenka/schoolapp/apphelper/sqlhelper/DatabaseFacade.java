package com.kirylkhrystsenka.schoolapp.apphelper.sqlhelper;


import com.kirylkhrystsenka.schoolapp.dao.entities.Group;
import com.kirylkhrystsenka.schoolapp.dao.entities.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseFacade {
    private DatabaseFacade(){

    }
    public static void createDatabase(Connection connection) throws SQLException, IOException {
        connection.setAutoCommit(false);
        try {

            if (SqlUtils.executeSqlScript(connection, "drop_db.sql") < 0) {
                throw new RuntimeException();
            }
            if (SqlUtils.executeSqlScript(connection, "init_scheme.sql") < 0) {
                throw new RuntimeException();
            }
            if (SqlUtils.executeSqlScript(connection, "generate_data.sql") < 0) {
                throw new RuntimeException();
            }
            connection.commit();
        } catch (SQLException | IOException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public static List<Group> findAllGroupsWithLessOrEqualStudentsNumber(Connection connection, int numberOfStudents) throws SQLException {
        String query = """
                SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count
                                                                          FROM groups
                                                                          LEFT JOIN students ON groups.group_id = students.group_id
                                                                          GROUP BY groups.group_id, groups.group_name
                                                                          HAVING COUNT(students.student_id) <= ?
                                                      					ORDER BY groups.group_id;
                """;
        List<Group> groups = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numberOfStudents);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group(resultSet.getLong("group_id"), resultSet.getString("group_name"));
                groups.add(group);
            }
        }catch (SQLException e){
            throw new SQLException("Request execution error", e);
        }
        return groups;
    }

    public static List<Student> findAllStudentsRelatedToTheCourse(Connection connection, String groupName) throws SQLException {
        String query = """
                SELECT s.student_id, s.first_name, s.last_name, g.group_id
                                       FROM students AS s
                                       JOIN groups AS g
                                       ON s.group_id = g.group_id
                                       WHERE g.group_name LIKE ?;
                """;
        List<Student> students = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();
            long groupId = -1L;
            if (resultSet.next()) {
                groupId = resultSet.getLong("group_id");
            }

            Group group = new Group(groupId, groupName);
            while (resultSet.next()) {
                Student student = new Student(resultSet.getLong("student_id"),
                        group,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                        );
                students.add(student);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return students;
    }

    public static int addStudent(Connection connection, int groupID, String studentName, String studentSurname) throws SQLException {
        String query = """
                INSERT INTO students(group_id, first_name, last_name)
                VALUES (?,?,?)
                """;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupID);
            statement.setString(2, studentName);
            statement.setString(3, studentSurname);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
        return result;
    }

    public static int deleteStudent(Connection connection, int studentId) throws SQLException {
        String query = """
                DELETE FROM student_courses WHERE student_id = ?;
                DELETE FROM students WHERE student_id = ?;
                """;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, studentId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
        return result;
    }

    public static int addStudentToTheCourse(Connection connection, int studentId, int courseId) throws SQLException {
        String query = """
                INSERT INTO student_courses (student_id, course_id)
                VALUES (?, ?);
                """;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
        return result;
    }

    public static int removeStudentFromTheCourse(Connection connection, int studentId, int courseId) throws SQLException {
        String query = """
                DELETE FROM student_courses WHERE student_id = ? AND course_id = ?;
                """;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
        return result;
    }
}

