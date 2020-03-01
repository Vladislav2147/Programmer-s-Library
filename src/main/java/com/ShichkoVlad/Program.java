package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.MyConnector;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.LibraryManagers.BookManager;
import com.ShichkoVlad.LibraryManagers.ReaderManager;
import com.ShichkoVlad.Reader.Reader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;
import java.time.Year;
import java.util.List;

public class Program {
    public static void main (String[] args) {

        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Logger logger = Logger.getLogger(Program.class);
        logger.info("--------Program begins--------");

        try(Connection connect = MyConnector.getConnection()) {

            BookManager bookManager = new BookManager();

            bookManager.takeBookFromReader(1, connect);
            bookManager.giveBookToReader(2, 1, connect);

            Book myBook = new Book(4, "my book", Year.of(2006));
            bookManager.addNewBook(myBook, connect);

            System.out.println("\nBooks from database: ");
            for(Book book: bookManager.getBooksFromDatabase(connect)) {
                System.out.println(book);
            }

            System.out.println("\nBooks that was published after 2000: ");
            for(Book book: bookManager.findByYear(Year.of(2000), Year.now(), connect)) {
                System.out.println(book);
            }

            System.out.println("\nBooks that was written by alexandr pushkin: ");
            for(Book book: bookManager.findByAuthorName("alexandr pushkin", connect)) {
                System.out.println(book);
            }

            bookManager.removeBook(myBook.getId(), connect);

        } catch(SQLException | AmbiguousFilterException | ReaderAlreadyHasBookException | NoSuchBookException e) {
            logger.error("exception" + e.getMessage());
            System.out.println(e);
        }

        try(Connection connect = MyConnector.getConnection()) {

            ReaderManager readerManager = new ReaderManager();

            System.out.println("\nReader with id = 1:");
            System.out.println(readerManager.getReaderById(1, connect));

            System.out.println("\nChange name of first reader: ");
            readerManager.changeReader(1, connect);
            System.out.println(readerManager.getReaderById(1, connect));

            Reader reader = new Reader(4, "name", "surname", "anotherreader@yandex.ru");

            readerManager.addNewReader(reader, connect);
            System.out.println("\nReader with email 'anotherreader@yandex.ru': ");
            System.out.println(readerManager.findByEmail("anotherreader@yandex.ru", connect));

            readerManager.removeReader(reader.getId(), connect);


        } catch(SQLException | AmbiguousFilterException | NoSuchReaderException e) {
            logger.error("exception" + e.getMessage());
            System.out.println(e);
        }

        logger.info("--------Program ends--------");
    }
}
