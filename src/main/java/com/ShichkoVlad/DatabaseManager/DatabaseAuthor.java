package com.ShichkoVlad.DatabaseManager;

import com.ShichkoVlad.Book.Author;
import lombok.Data;

import java.sql.Connection;

@Data
public class DatabaseAuthor implements ISqlable<Author> {
    public static String table = "authors";
    @Override
    public String getTable() {
        return table;
    }

    @Override
    public Author getInstanceById(int id, Connection connection) {
        return null;
    }

    @Override
    public boolean addToTable(Author obj, Connection connection) {
        return false;
    }

    @Override
    public boolean removeFromTable(int id, Connection connection) {
        return false;
    }

    @Override
    public boolean changeInTable(int id, Connection connection) {
        return false;
    }
}
