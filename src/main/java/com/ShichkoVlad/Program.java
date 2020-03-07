package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.MyConnector;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.JSONSerialization.JsonListSerializer;
import com.ShichkoVlad.LibraryManagers.BookManager;
import com.ShichkoVlad.LibraryManagers.ReaderManager;
import com.ShichkoVlad.Reader.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Program {
    public static void main (String[] args) {

        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Logger logger = Logger.getLogger(Program.class);
        logger.info("--------Program begins--------");

        try(Connection connect = MyConnector.getConnection()) {

            BookManager bookManager = new BookManager();
            ReaderManager readerManager = new ReaderManager();
            List<Reader> readers = readerManager.getListFromDatabase(connect);
            List<Book> books = bookManager.getListFromDatabase(connect);

            System.out.println(readers);
            JsonListSerializer<Book> bookJsonListSerializer = new JsonListSerializer<>();
            JsonListSerializer<Reader> readerJsonListSerializer = new JsonListSerializer<>();


        } catch(SQLException /*| IOException | ReaderAlreadyHasBookException | NoSuchBookException*/ e) {
            logger.error("exception" + e.getMessage());
            System.out.println(e);
        }

        logger.info("--------Program ends--------");
    }
}
