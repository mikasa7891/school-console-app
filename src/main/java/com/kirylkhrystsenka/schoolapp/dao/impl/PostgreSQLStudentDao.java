package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.AbstractCrudDao;
import com.kirylkhrystsenka.schoolapp.dao.GroupDAO;
import com.kirylkhrystsenka.schoolapp.dao.entities.Course;
import com.kirylkhrystsenka.schoolapp.dao.entities.Student;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PostgreSQLStudentDao extends AbstractCrudDao<Student,Long> implements GroupDAO {

    @Override
    protected Student create(Connection connection, Student entity) {
        return null;
    }

    @Override
    protected Student update(Connection connection, Student entity) {
        return null;
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
        return null;
    }

    @Override
    public void deleteById(Connection connection, Long id) {
        System.out.println("");
    }

    @Override
    public Optional<Course> findByName(Connection connection, String name) {
        return Optional.empty();
    }
}
