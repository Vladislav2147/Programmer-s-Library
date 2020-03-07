package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.MyConnector;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.JsonSerialization.JsonListSerializer;
import com.ShichkoVlad.LibraryManagers.BookManager;
import com.ShichkoVlad.LibraryManagers.ReaderManager;
import com.ShichkoVlad.Reader.Reader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.sql.*;
import java.time.Year;

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
            bookManager.addNew(myBook, connect);

            System.out.println("\nBooks from database: ");
            for(Book book: bookManager.getListFromDatabase(connect)) {
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

            bookManager.remove(myBook.getId(), connect);

            JsonListSerializer<Book> bookJsonListSerializer = new JsonListSerializer<>();
            bookJsonListSerializer.Serialize(bookManager.getListFromDatabase(connect), "books.json");

            System.out.println("\nBooks from json file: ");
            System.out.println(bookJsonListSerializer.Deserialize("books.json"));


        } catch(SQLException | ReaderAlreadyHasBookException | NoSuchBookException | IOException e) {
            logger.error("exception" + e.getMessage());
            System.out.println(e);
        }

        try(Connection connect = MyConnector.getConnection()) {

            ReaderManager readerManager = new ReaderManager();

            System.out.println("\nReader with id = 1:");
            System.out.println(readerManager.getById(1, connect));

            System.out.println("\nChange name of first reader: ");
            readerManager.changeItem(1, connect);
            System.out.println(readerManager.getById(1, connect));

            Reader reader = new Reader(4, "name", "surname", "anotherreader@yandex.ru");

            readerManager.addNew(reader, connect);
            System.out.println("\nReader with email 'anotherreader@yandex.ru': ");
            System.out.println(readerManager.findByEmail("anotherreader@yandex.ru", connect));

            readerManager.remove(reader.getId(), connect);

            JsonListSerializer<Reader> readerJsonListSerializer = new JsonListSerializer<>();
            readerJsonListSerializer.Serialize(readerManager.getListFromDatabase(connect), "books.json");

            System.out.println("\nReaders from json file: ");
            System.out.println(readerJsonListSerializer.Deserialize("readers.json"));

        } catch(SQLException | NoSuchReaderException | IOException e) {
            logger.error("exception" + e.getMessage());
            System.out.println(e);
        }

        logger.info("--------Program ends--------");
    }
}
