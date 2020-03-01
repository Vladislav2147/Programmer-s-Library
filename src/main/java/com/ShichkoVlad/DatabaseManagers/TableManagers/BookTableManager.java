package com.ShichkoVlad.DatabaseManagers.TableManagers;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Book.Publisher;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

public class BookTableManager implements ITableManageable<Book> {

    static final String tableName = "books";

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

        if(book.getPublisher() != null) {
            statement.setInt(4, book.getPublisher().getId());
        }
        else {
            statement.setObject(4, null);
        }

        if(book.getPublishingDate() != null) {
            statement.setDate(5, Date.valueOf(book.getPublishingDate()));
        }
        else {
            statement.setObject(5, null);
        }

        if(book.getPublishingDate() != null) {
            statement.setInt(6, book.getCopiesAmount());
        }
        else {
            statement.setObject(6, null);
        }

        statement.executeUpdate();
    }

}
