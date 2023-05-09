package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents() throws SQLException;
    Student getStudentById(int id) throws SQLException;
    void addStudent(Student student) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(Student student) throws SQLException;
}
