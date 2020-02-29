package com.ShichkoVlad.DatabaseManagers;

import com.ShichkoVlad.Exceptions.AmbiguousFilterException;
import com.ShichkoVlad.Reader.Reader;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.Year;
import java.util.Optional;

public class ReaderTableManager implements ITableManagable<Reader> {

    static String tableName = "readers";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Reader getInstanceById(int id, Connection connection) throws SQLException, AmbiguousFilterException {

        ResultSet resultSet = getRowById(id, connection);
        Reader reader = new Reader();

        reader.setId(resultSet.getInt("id"));
        reader.setFirstName(resultSet.getString("first_name"));
        reader.setSecondName(resultSet.getString("second_name"));
        reader.setEmail(resultSet.getString("email"));

        Optional.ofNullable(resultSet.getObject("birth_year")).ifPresent((year) -> {
            reader.setBirthYear(Year.of((int)year));
        });

        Optional.ofNullable(resultSet.getObject("phone")).ifPresent((phone) -> {
            reader.setPhone(phone.toString());
        });

        return reader;

    }

    @Override
    public void addToTable(Reader reader, Connection connection) throws SQLException {
        String query = "INSERT INTO " + getTableName() + " (id, first_name, second_name, birth_year, email, phone, book_id) "
                + "value (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, reader.getId());
        statement.setString(2, reader.getFirstName());
        statement.setString(3, reader.getSecondName());
        Optional.ofNullable(reader.getBirthYear()).ifPresent((year -> {
            try {
                statement.setInt(4, reader.getBirthYear().getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        statement.setString(5, reader.getEmail());

        Optional.ofNullable(reader.getPhone()).ifPresent((phone -> {
            try {
                statement.setString(6, phone);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        reader.getBook().ifPresent((book -> {
            try {
                statement.setInt(7, book.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        statement.executeUpdate();

    }

    public void takeBookFromReader(int readerId, Connection connection) throws SQLException{

        String query = "UPDATE " + getTableName() + " SET book_id = NULL WHERE id = " + readerId + ";";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();

    }
}
