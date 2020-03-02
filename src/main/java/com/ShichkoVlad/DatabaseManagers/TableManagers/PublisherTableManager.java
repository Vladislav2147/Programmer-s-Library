package com.ShichkoVlad.DatabaseManagers.TableManagers;

import com.ShichkoVlad.Book.Country;
import com.ShichkoVlad.Book.Publisher;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherTableManager implements ITableManager<Publisher> {

    static final String tableName = "publishers";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Publisher getInstanceById(int id, Connection connection) throws SQLException {

        ResultSet resultSet = getRowById(id, connection);
        Publisher publisher = new Publisher();

        publisher.setId(resultSet.getInt("id"));
        publisher.setName(resultSet.getString("name"));
        publisher.setCountry(Country.valueOf(resultSet.getString("country")));
        publisher.setAddress(resultSet.getString("address"));
        publisher.setPostCode(resultSet.getString("post_code"));
        publisher.setEmail(resultSet.getString("email"));

        return publisher;

    }


    @Override
    public void addToTable(Publisher publisher, Connection connection) throws SQLException {

        String query = "INSERT INTO " + getTableName() + " (id, name, country, address, post_code, email) "
                + "value (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, publisher.getId());
        statement.setString(2, publisher.getName());
        statement.setString(3, publisher.getCountry().toString());
        statement.setString(4, publisher.getAddress());
        statement.setString(5, publisher.getPostCode());
        statement.setString(6, publisher.getEmail());

        statement.executeUpdate();
    }

}
