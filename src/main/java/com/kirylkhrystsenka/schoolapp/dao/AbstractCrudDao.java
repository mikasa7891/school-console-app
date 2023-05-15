package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.dao.entities.HasId;

import java.sql.Connection;

public abstract class AbstractCrudDao<T extends HasId<K>, K> {

    protected abstract T create(Connection connection, T entity);

    protected abstract T update(Connection connection, T entity);

}
