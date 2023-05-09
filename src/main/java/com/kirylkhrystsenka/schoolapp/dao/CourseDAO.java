package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.domain.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    List<Course> getAllCourses() throws SQLException;

    Course getCourseById(int id) throws SQLException;

    void addCourse(Course course) throws SQLException;

    void updateCourse(Course course) throws SQLException;

    void deleteCourse(Course course) throws SQLException;

}
