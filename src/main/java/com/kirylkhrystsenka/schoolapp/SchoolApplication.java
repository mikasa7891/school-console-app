package com.kirylkhrystsenka.schoolapp;

import com.kirylkhrystsenka.schoolapp.sqlhelper.SQLTasks;

import java.util.Scanner;

public class SchoolApplication {
    public static void main(String[] args){
        SQLTasks sqlTasks = new SQLTasks();
        sqlTasks.createDatabase();
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
            String result;
                option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case (1) -> {
                        System.out.println("number of students - ");
                        int numberOfStudents = sc.nextInt();
                        result = sqlTasks.findAllGroupsWithLessOrEqualStudentsNumber(numberOfStudents);
                        System.out.println(result);
                    }
                    case (2) -> {
                        System.out.println("group name - ");
                        String groupName = sc.nextLine();
                        result = sqlTasks.findAllStudentsRelatedToTheCourse(groupName);
                        System.out.println(result);
                    }
                    case (3) -> {
                        System.out.println("Write group_id, name and surname of student using spaces\n");
                        String input = sc.nextLine();
                        String[] studentsData = input.split(" ");
                        int numberOfLinesAdded = sqlTasks.addStudent(Integer.parseInt(studentsData[0]), studentsData[1], studentsData[2]);
                        System.out.println("Number of lines added - " + numberOfLinesAdded);
                    }
                    case (4) -> {
                        System.out.println("Write student's ID");
                        int studentId = sc.nextInt();
                        int numberOfLinesRemoved = sqlTasks.deleteStudent(studentId);
                        System.out.println("Number of lines removed - " + numberOfLinesRemoved);
                    }
                    case (5) -> {
                        System.out.println("Write student's ID and course ID");
                        int studentId = sc.nextInt();
                        int courseID = sc.nextInt();
                        int numberOfLinesAdded = sqlTasks.addStudentToTheCourse(studentId, courseID);
                        System.out.println("Number of lines added - " + numberOfLinesAdded);
                    }
                    case (6) -> {
                        System.out.println("Write student's ID and course ID");
                        int studentId = sc.nextInt();
                        int courseID = sc.nextInt();
                        int numberOfLinesRemoved = sqlTasks.removeStudentFromTheCourse(studentId, courseID);
                        System.out.println("Number of lines removed - " + numberOfLinesRemoved);
                    }
                    default -> isAlive = false;
                }
            }
        }
    }
}
