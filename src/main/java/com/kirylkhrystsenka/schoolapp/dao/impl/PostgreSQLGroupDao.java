package com.kirylkhrystsenka.schoolapp.dao.impl;

import com.kirylkhrystsenka.schoolapp.dao.GroupDAO;
import com.kirylkhrystsenka.schoolapp.domain.Group;

import java.sql.SQLException;
import java.util.List;

public class PostgreSQLGroupDao implements GroupDAO {
    @Override
    public List<Group> getAllGroups() throws SQLException {
        return null;
    }

    @Override
    public Group getGroupById(int id) throws SQLException {
        return null;
    }

    @Override
    public void addGroup(Group group) throws SQLException {

    }

    @Override
    public void updateGroup(Group group) throws SQLException {

    }

    @Override
    public void deleteGroup(Group group) throws SQLException {

    }
}
