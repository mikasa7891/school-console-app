package com.kirylkhrystsenka.schoolapp.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T,K> {
    Optional<T> findById(Connection connection, K id);
    List<T> findAll(Connection connection);
    T save(Connection connection, T entity);
    void deleteById(Connection connection, K id);

}
