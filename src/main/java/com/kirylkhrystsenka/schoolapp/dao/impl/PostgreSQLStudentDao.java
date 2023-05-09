package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.StudentDAO;
import com.kirylkhrystsenka.schoolapp.domain.Student;

import java.sql.SQLException;
import java.util.List;

public class PostgreSQLStudentDao implements StudentDAO {
    @Override
    public List<Student> getAllStudents() throws SQLException {
        return null;
    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        return null;
    }

    @Override
    public void addStudent(Student student) throws SQLException {

    }

    @Override
    public void updateStudent(Student student) throws SQLException {

    }

    @Override
    public void deleteStudent(Student student) throws SQLException {

    }
}
