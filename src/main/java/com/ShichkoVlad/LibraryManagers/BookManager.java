package com.ShichkoVlad.LibraryManagers;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.*;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.Reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class BookManager {

    public void addNewBook(Book book, Connection connection) throws SQLException {

        PublisherTableManager publisherTableManager = new PublisherTableManager();
        publisherTableManager.addToTable(book.getPublisher(), connection);

        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.addToTable(book, connection);

        AuthorTableManager authorTableManager = new AuthorTableManager();
        for(Author author: book.getAuthors()) {
            try {
                authorTableManager.addToTable(author, connection);
                WrittenByTableManager.setAuthorOfBook(book.getId(), author.getId(), connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void changeBook(int bookId, Connection connection) throws SQLException, AmbiguousFilterException {

        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.changeInTable(bookId, "title", "new title", connection);

    }

    public void removeBook(int bookId, Connection connection) throws SQLException, AmbiguousFilterException {

        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.removeFromTable(bookId, connection);

    }

    //Выдача книги читателю
    public void giveBookToReader(int bookId, int readerId, Connection connection) throws SQLException, AmbiguousFilterException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.changeInTable(readerId, "book_id", bookId, connection);

    }

    //Забрать книгу у читателя
    public void takeBookFromReader(int readerId, Connection connection) throws SQLException, AmbiguousFilterException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.takeBookFromReader(readerId, connection);

    }


}
