package com.ShichkoVlad.DatabaseManagers;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Book.Publisher;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

public class BookTableManager implements ITableManagable<Book> {

    static String tableName = "books";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Book getInstanceById(int id, Connection connection) throws SQLException, AmbiguousFilterException {

        ResultSet resultSet = getRowById(id, connection);
        Book book = new Book();

        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("title"));
        book.setYear(Year.of(resultSet.getInt("publishing_year")));
        //TODO вынести в bookmanager
        if(resultSet.getObject("publisher_id") != null) {

            int publisherId = resultSet.getInt("publisher_id");
            PublisherTableManager publisherTableManager = new PublisherTableManager();
            Publisher publisher = publisherTableManager.getInstanceById(publisherId, connection);
            book.setPublisher(publisher);

        }

        Optional.ofNullable(resultSet.getObject("publishing_date")).ifPresent((date) -> {
            book.setPublishingDate(LocalDate.parse(date.toString()));
        });

        Optional.ofNullable(resultSet.getObject("copies")).ifPresent((copies) -> {
            book.setCopiesAmount((int)copies);
        });
        //TODO вынести в bookmanager
        book.setAuthors(WrittenByTableManager.getAuthorsByBookId(id, connection));

        return book;
    }

    @Override
    public void addToTable(Book book, Connection connection) throws SQLException {
        String query = "INSERT INTO " + getTableName() + " (id, title, publishing_year, publisher_id, publishing_date, copies) "
                + "value (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, book.getId());
        statement.setString(2, book.getName());
        statement.setInt(3, book.getYear().getValue());
        Optional.ofNullable(book.getPublisher()).ifPresent((publisher -> {
            try {
                statement.setInt(4, publisher.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        Optional.ofNullable(book.getPublishingDate()).ifPresent((date -> {
            try {
                statement.setDate(5, Date.valueOf(date));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        Optional.ofNullable(book.getCopiesAmount()).ifPresent((copies -> {
            try {
                statement.setInt(6, copies);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        statement.executeUpdate();
    }

}
