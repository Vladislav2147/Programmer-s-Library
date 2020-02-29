package com.ShichkoVlad.DatabaseManagers;

import com.ShichkoVlad.Book.Country;
import com.ShichkoVlad.Book.Publisher;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePublisherManager implements ISqlable<Publisher> {

    static String tableName = "publishers";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Publisher getInstanceById(int id, Connection connection) throws SQLException, AmbiguousFilterException {

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

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO " + getTableName() + " value (");
        query.append(publisher.getId() + ",");
        query.append("'" + publisher.getName() + "',");
        query.append("'" + publisher.getCountry().toString() + "',");
        query.append("'" + publisher.getAddress() + "',");
        query.append("'" + publisher.getPostCode() + "',");
        query.append("'" + publisher.getEmail() + "');");

        PreparedStatement statement = connection.prepareStatement(query.toString());
        statement.executeUpdate();

    }

}
