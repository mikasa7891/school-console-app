package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.dao.entities.Course;
import com.kirylkhrystsenka.schoolapp.dao.entities.Group;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GroupDAO extends CrudDao<Course, Long> {
    Optional<Course> findByName(Connection connection, String name);
}
