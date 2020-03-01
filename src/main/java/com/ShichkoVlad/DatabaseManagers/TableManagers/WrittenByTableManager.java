package com.ShichkoVlad.DatabaseManagers.TableManagers;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WrittenByTableManager {

    static final String tableName = "written_by";

    public static List<Author> getAuthorsByBookId(int bookId, Connection connection) throws SQLException, AmbiguousFilterException {

        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM " + tableName + " WHERE book_id = " + bookId + ";");
        ResultSet resultSet = statement.executeQuery();

        AuthorTableManager authorTableManager = new AuthorTableManager();
        List<Author> authors = new ArrayList<>();
        int authorId;

        while (resultSet.next()) {

            authorId = resultSet.getInt("author_id");
            authors.add(authorTableManager.getInstanceById(authorId, connection));
        }

        return authors;

    }

    public static void setAuthorOfBook(int bookId, int authorId, Connection connection) throws SQLException {

        String query = "INSERT INTO " + tableName + " (author_id, book_id) value (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, authorId);
        statement.setInt(2, bookId);

        statement.executeUpdate();

    }
}
