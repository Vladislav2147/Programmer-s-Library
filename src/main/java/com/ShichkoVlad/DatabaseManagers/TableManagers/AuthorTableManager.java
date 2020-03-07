package com.ShichkoVlad.DatabaseManagers.TableManagers;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class AuthorTableManager implements ITableManager<Author> {

    static final String tableName = "authors";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Author getInstanceById(int id, Connection connection) throws SQLException {

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

        String query = "INSERT INTO " + getTableName() + " (id, first_name, second_name, birth_year, gender, note) "
                + "value (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, author.getId());
        statement.setString(2, author.getFirstName());
        statement.setString(3, author.getSecondName());
        statement.setInt(4, author.getBirthYear().getValue());
        statement.setString(5, author.getGender().toString());
        statement.setString(6, author.getNote());

        statement.executeUpdate();

    }

}