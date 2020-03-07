package com.ShichkoVlad.DatabaseManagers;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyConnector {
    @Test
    public void getConnection() throws SQLException {

        ResourceBundle resource = ResourceBundle.getBundle("db", Locale.getDefault());

        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

        Connection connection = DriverManager.getConnection(url, user, pass);
        Assert.assertNotNull("not null",connection);

    }
}
