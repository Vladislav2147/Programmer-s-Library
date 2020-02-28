package com.ShichkoVlad.DatabaseManagers;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Gender;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class DatabaseAuthorManager implements ISqlable<Author> {

    static String tableName = "authors";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Author getInstanceById(int id, Connection connection) throws SQLException, AmbiguousFilterException {

        ResultSet resultSet = getRowById(id, connection);
        Author author = new Author();

        author.setId(resultSet.getInt("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setSecondName(resultSet.getString("second_name"));
        author.setBirthYear(Year.of(resultSet.getInt("birth_year")));
        author.setGender(Gender.valueOf(resultSet.getString("gender")));
        author.setNote(resultSet.getString("note"));

        return author;
    }

    @Override
    public void addToTable(Author author, Connection connection) throws SQLException {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO " + getTableName() + " value (");
        query.append(author.getId() + ",");
        query.append("'" + author.getFirstName() + "',");
        query.append("'" + author.getSecondName() + "',");
        query.append(author.getBirthYear().getValue() + ",");
        query.append("'" + author.getGender().toString() + "',");
        query.append("'" + author.getNote() + "');");

        PreparedStatement statement = connection.prepareStatement(query.toString());
        statement.executeUpdate();

    }

    @Override
    public void changeInTable(int id, String setStatement, Connection connection) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE " + getTableName());
        query.append(" SET " + setStatement);
        query.append(" WHERE id = " + id);

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
        preparedStatement.executeUpdate();

    }
}
