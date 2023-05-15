package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.dao.entities.Course;

import java.sql.Connection;
import java.util.Optional;

public interface CourseDao extends CrudDao<Course, Long> {
    Optional<Course> findByName(Connection connection, String name);

}
