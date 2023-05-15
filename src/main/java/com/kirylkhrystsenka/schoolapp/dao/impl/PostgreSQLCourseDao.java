package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.CourseDao;
import com.kirylkhrystsenka.schoolapp.dao.entities.Course;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PostgreSQLCourseDao extends AbstractCrudDao<Course,Long> implements CourseDao{

    @Override
    public Optional<Course> findByName(Connection connection, String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> findById(Connection connection, Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> findAll(Connection connection) {
        return null;
    }

    @Override
    public Course save(Connection connection, Course entity) {
        return entity.getId() == null ? create(connection, entity) : update(connection, entity);
    }

    @Override
    public void deleteById(Connection connection, Long id) {

    }

    @Override
    protected Course create(Connection connection, Course entity) {
        return null;
    }

    @Override
    protected Course update(Connection connection, Course entity) {
        return null;
    }
}
