package com.codecool_mjs.dataaccess.dao;

import com.codecool_mjs.model.Group;
import com.codecool_mjs.model.Quest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDao extends Dao<Group> {

    @Override
    Group createObject(ResultSet results) throws SQLException {

        Integer id = results.getInt("id");
        String name = results.getString("name");

        Group group = new Group(id, name);

        return group;
    }

    @Override
    String getQueryForGetAll() {

        String query = "SELECT * FROM groups";

        return query;
    }

    @Override
    String getQueryForSearchBy(String category, String arg) {

        String query = String.format("SELECT * FROM groups WHERE %s LIKE '%s'", category, arg);

        return query;
    }

    @Override
    Integer executeInsertation(Group group) throws SQLException {
        return null;
    }

    @Override
    Integer executeDeletion(Group group) throws SQLException {
        return null;
    }
}
