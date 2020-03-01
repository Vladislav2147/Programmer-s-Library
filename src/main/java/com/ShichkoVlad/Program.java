//TODO
// Вызывать его методы в менеджерах
// Поиск, возвращение списка объектов из бд, readermanager
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.BookTableManager;
import com.ShichkoVlad.DatabaseManagers.MyConnector;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.LibraryManagers.BookManager;

import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Program {
    public static void main (String[] args) {



        try(Connection connect = MyConnector.getConnection()) {
            BookManager bookManager = new BookManager();
            bookManager.takeBookFromReader(1, connect);
            bookManager.giveBookToReader(2, 1, connect);
            List<Book> books = bookManager.getBooksFromDatabase(connect);
            for(Book book: books) {
                System.out.println(book);
            }
        } catch(SQLException | AmbiguousFilterException | ReaderAlreadyHasBookException e) {

            System.out.println(e);
        }
    }
}
