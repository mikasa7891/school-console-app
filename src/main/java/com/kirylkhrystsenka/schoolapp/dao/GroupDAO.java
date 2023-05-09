package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.domain.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupDAO {
    List<Group> getAllGroups() throws SQLException;
    Group getGroupById(int id) throws SQLException;
    void addGroup(Group group) throws SQLException;
    void updateGroup(Group group) throws SQLException;
    void deleteGroup(Group group) throws SQLException;
}
