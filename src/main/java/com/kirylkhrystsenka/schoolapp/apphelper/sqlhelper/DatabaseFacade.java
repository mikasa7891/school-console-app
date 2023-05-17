package com.kirylkhrystsenka.schoolapp.apphelper.sqlhelper;


import java.io.IOException;
import java.sql.*;


public class DatabaseFacade {
    private DatabaseFacade(){

    }
    public static void createDatabase(Connection connection) throws SQLException, IOException {
        connection.setAutoCommit(false);
        try {
            // Выполнение скриптов
            if (SqlUtils.executeSqlScript(connection, "drop_db.sql") < 0) {
                throw new RuntimeException();
            }
            if (SqlUtils.executeSqlScript(connection, "init_scheme.sql") < 0) {
                throw new RuntimeException();
            }
            if (SqlUtils.executeSqlScript(connection, "generate_data.sql") < 0) {
                throw new RuntimeException();
            }
            connection.commit(); // Фиксация транзакции
        } catch (SQLException | IOException e) {
            connection.rollback(); // Откат транзакции при ошибке
            throw e;
        } finally {
            connection.setAutoCommit(true); // Восстановление режима автокоммита
        }
    }

    public static String findAllGroupsWithLessOrEqualStudentsNumber(Connection connection, int numberOfStudents) {
        String query = """
                SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count
                                                                          FROM groups
                                                                          LEFT JOIN students ON groups.group_id = students.group_id
                                                                          GROUP BY groups.group_id, groups.group_name
                                                                          HAVING COUNT(students.student_id) <= ?
                                                      					ORDER BY groups.group_id;
                """;
        StringBuilder result = new StringBuilder();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

    public static String findAllStudentsRelatedToTheCourse(Connection connection, String groupName) {
        String query = """
                SELECT s.first_name, s.last_name, g.group_name
                                       FROM students AS s
                                       JOIN groups AS g
                                       ON s.group_id = g.group_id
                                       WHERE g.group_name LIKE ?
                """;
        StringBuilder result = new StringBuilder();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.append(resultSet.getString("first_name")).append(" - ");
                result.append(resultSet.getString("last_name")).append(" - ");
                result.append(resultSet.getString("group_name")).append(System.lineSeparator());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.length() == 0 ? "There is no such group in database" : result.toString().trim();
    }

    public static int addStudent(Connection connection, int groupID, String studentName, String studentSurname) {
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
            e.printStackTrace();
        }
        return result;
    }

    public static int deleteStudent(Connection connection, int studentId) {
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
            e.printStackTrace();
        }
        return result;
    }

    public static int addStudentToTheCourse(Connection connection, int studentId, int courseId) {
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
            e.printStackTrace();
        }
        return result;
    }

    public static int removeStudentFromTheCourse(Connection connection, int studentId, int courseId) {
        String query = """
                DELETE FROM student_courses WHERE student_id = ? AND course_id = ?;
                """;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

