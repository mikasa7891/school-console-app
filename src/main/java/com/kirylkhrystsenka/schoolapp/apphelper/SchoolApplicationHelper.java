package com.kirylkhrystsenka.schoolapp.apphelper;

import com.kirylkhrystsenka.schoolapp.apphelper.sqlhelper.DatabaseFacade;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SchoolApplicationHelper implements AutoCloseable{
    HikariDataSource dataSource;
    public SchoolApplicationHelper(HikariDataSource dataSource){
        this.dataSource = dataSource;
        try(Connection connection = dataSource.getConnection()) {
            DatabaseFacade.createDatabase(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        boolean isAlive = true;
        try (Scanner sc = new Scanner(System.in)) {
            while (isAlive) {
                System.out.println("""
                               -------------------------------------------------------
                               1. Find all groups with less or equal students’ number
                               2. Find all students related to the course with the given name
                               3. Add a new student
                               4. Delete a student by the STUDENT_ID
                               5. Add a student to the course (from a list)
                               6. Remove the student from one of their courses
                               
                               Choose your option -
                               """);

                int option;
                String result = null;
                option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case (1) -> {
                        System.out.println("number of students - ");
                        int numberOfStudents = sc.nextInt();
                        try(Connection connection = dataSource.getConnection()) {
                            result = DatabaseFacade.findAllGroupsWithLessOrEqualStudentsNumber(connection, numberOfStudents);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                    }
                    case (2) -> {
                        System.out.println("group name - ");
                        String groupName = sc.nextLine();
                        try(Connection connection = dataSource.getConnection()) {
                            result = DatabaseFacade.findAllStudentsRelatedToTheCourse(connection, groupName);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                    }
                    case (3) -> {
                        System.out.println("Write group_id, name and surname of student using spaces\n");
                        String input = sc.nextLine();
                        String[] studentsData = input.split(" ");
                        int numberOfLinesAdded = 0;
                        try(Connection connection = dataSource.getConnection()) {
                           numberOfLinesAdded = DatabaseFacade.addStudent(connection, Integer.parseInt(studentsData[0]), studentsData[1], studentsData[2]);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Number of lines added - " + numberOfLinesAdded);
                    }
                    case (4) -> {
                        System.out.println("Write student's ID");
                        int studentId = sc.nextInt();
                        int numberOfLinesRemoved = 0;
                        try(Connection connection = dataSource.getConnection()) {
                            numberOfLinesRemoved = DatabaseFacade.deleteStudent(connection, studentId);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Number of lines removed - " + numberOfLinesRemoved);
                    }
                    case (5) -> {
                        System.out.println("Write student's ID and course ID");
                        int studentId = sc.nextInt();
                        int courseID = sc.nextInt();
                        int numberOfLinesAdded = 0;
                        try(Connection connection = dataSource.getConnection()) {
                            numberOfLinesAdded = DatabaseFacade.addStudentToTheCourse(connection, studentId, courseID);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Number of lines added - " + numberOfLinesAdded);
                    }
                    case (6) -> {
                        System.out.println("Write student's ID and course ID");
                        int studentId = sc.nextInt();
                        int courseID = sc.nextInt();
                        int numberOfLinesRemoved = 0;
                        try(Connection connection = dataSource.getConnection()) {
                            numberOfLinesRemoved = DatabaseFacade.removeStudentFromTheCourse(connection, studentId, courseID);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Number of lines removed - " + numberOfLinesRemoved);
                    }
                    default ->{
                        isAlive = false;
                        System.out.println("The end of the program");
                    }
                }
            }
        }
    }

    @Override
    public void close(){
        dataSource.close();
    }
}
