package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.CourseDAO;
import com.kirylkhrystsenka.schoolapp.domain.Course;

import java.sql.SQLException;
import java.util.List;

public class PostgreSQLCourseDao implements CourseDAO {
    @Override
    public List<Course> getAllCourses() throws SQLException {
        return null;
    }

    @Override
    public Course getCourseById(int id) throws SQLException {
        return null;
    }

    @Override
    public void addCourse(Course course) throws SQLException {

    }

    @Override
    public void updateCourse(Course course) throws SQLException {

    }

    @Override
    public void deleteCourse(Course course) throws SQLException {

    }
}
