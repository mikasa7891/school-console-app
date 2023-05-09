package com.kirylkhrystsenka.schoolapp;

import com.kirylkhrystsenka.schoolapp.sqlhelper.SQLTasks;

import java.sql.*;
import java.util.Scanner;

public class SchoolApplication {
    public static void main(String[] args) throws SQLException {
        SQLTasks sqlTasks = new SQLTasks();
        sqlTasks.createDatabase();
        boolean isAlive = true;
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
            String result;
            try (Scanner sc = new Scanner(System.in)) {
                option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case (1) -> {
                        System.out.println("number of students - ");
                        int numberOfStudents = sc.nextInt();
                        result = sqlTasks.findAllGroupsWithLessOrEqualStudentsNumber(numberOfStudents);
                        System.out.println(result);
                        sc.nextLine();
                    }
                    case (2) -> {
                        System.out.println("group name - ");
                        String groupName = sc.nextLine();
                        result = sqlTasks.findAllStudentsRelatedToTheCourse(groupName);
                        System.out.println(result);
                        sc.nextLine();
                    }
                    case (3) -> {
                        System.out.println("Write group_id, name and surname of student using spaces\n");
                        String input = sc.nextLine();
                        String[] studentsData = input.split(" ");
                        int numberOfLinesAdded = sqlTasks.addStudent(Integer.parseInt(studentsData[0]), studentsData[1], studentsData[2]);
                        System.out.println("numberOfLinesAdded - " + numberOfLinesAdded);
                        sc.nextLine();
                    }
                    default -> isAlive = false;
                }
            }
        }
    }
}
