package com.ShichkoVlad.DatabaseManager;

import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISqlable<T> {
    String getTable();
    T getInstanceById(int id, Connection connection);
    boolean addToTable(T obj, Connection connection);
    boolean removeFromTable(int id, Connection connection);
    boolean changeInTable(int id, Connection connection);

    default int getIdByFilter(String filter, Connection connection) throws SQLException, AmbiguousFilterException {
        PreparedStatement statement =
                connection.prepareStatement("SELECT id FROM " + getTable() + " WHERE " + filter + ";");

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        if(resultSet.next()) {
            throw new AmbiguousFilterException("ambiguous filter: " + filter);
        }
        return id;
    }


}
