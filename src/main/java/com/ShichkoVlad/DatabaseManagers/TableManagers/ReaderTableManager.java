package com.ShichkoVlad.DatabaseManagers.TableManagers;

import com.ShichkoVlad.Reader.Reader;

import java.sql.*;
import java.time.Year;
import java.util.Optional;

public class ReaderTableManager implements ITableManager<Reader> {

    static final String tableName = "readers";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Reader getInstanceById(int id, Connection connection) throws SQLException {

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

        Optional.ofNullable(resultSet.getObject("book_id")).ifPresent((bookId) -> {
            BookTableManager bookTableManager = new BookTableManager();
            try {
                reader.setBook(bookTableManager.getInstanceById((int)bookId, connection));
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        if(reader.getBirthYear() != null) {
            statement.setInt(4, reader.getBirthYear().getValue());
        }
        else {
            statement.setObject(4, null);
        }

        statement.setString(5, reader.getEmail());

        if(reader.getPhone() != null) {
            statement.setString(6, reader.getPhone());
        }
        else {
            statement.setObject(6, null);
        }

        if(reader.getBook() != null) {
            statement.setInt(7, reader.getBook().getId());
        }
        else {
            statement.setObject(7, null);
        }

        statement.executeUpdate();

    }

    public void takeBookFromReader(int readerId, Connection connection) throws SQLException{

        String query = "UPDATE " + getTableName() + " SET book_id = NULL WHERE id = " + readerId + ";";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();

    }
}
