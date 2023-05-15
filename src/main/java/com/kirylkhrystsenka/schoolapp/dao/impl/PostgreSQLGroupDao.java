package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.GroupDAO;
import com.kirylkhrystsenka.schoolapp.dao.entities.Course;
import com.kirylkhrystsenka.schoolapp.dao.entities.Group;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostgreSQLGroupDao extends AbstractCrudDao<Group,Long> implements GroupDAO {

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
        return null;
    }

    @Override
    public void deleteById(Connection connection, Long id) {

    }

    @Override
    public Optional<Course> findByName(Connection connection, String name) {
        return Optional.empty();
    }

    @Override
    protected Group create(Connection connection, Group entity) {
        return null;
    }

    @Override
    protected Group update(Connection connection, Group entity) {
        return null;
    }
}
