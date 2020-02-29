package com.ShichkoVlad.DatabaseManagers;

import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISqlable<T> {

    String getTableName();
    T getInstanceById(int id, Connection connection) throws SQLException, AmbiguousFilterException;
    void addToTable(T object, Connection connection) throws SQLException;

    //TODO изменить метод
    //На данном этапе метод changeInTable принимает параметром команду, согласно которой изменяет запись с соотв. id
    default void changeInTable(int id, String setStatement, Connection connection) throws SQLException, AmbiguousFilterException {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE " + getTableName());
        query.append(" SET " + setStatement);
        query.append(" WHERE id = " + id);

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
        preparedStatement.executeUpdate();

    }


    default int getIdByFilter(String filter, Connection connection) throws SQLException, AmbiguousFilterException {

        PreparedStatement statement =
                connection.prepareStatement("SELECT id FROM " + getTableName() + " WHERE " + filter + ";");

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");

        if(resultSet.isLast()) {
            return id;
        }
        else {
            throw new AmbiguousFilterException("ambiguous filter: " + filter);
        }
    }


    default ResultSet getRowById(int id, Connection connection) throws SQLException, AmbiguousFilterException {

        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE id = " + id + ";");

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        if (resultSet.isLast()) {
            return resultSet;
        }
        else {
            throw new AmbiguousFilterException("ambiguous id: " + id);
        }
    }


    default void removeFromTable(int id, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("DELETE FROM authors WHERE id = " + id + ";");
        statement.executeUpdate();

    }

}
