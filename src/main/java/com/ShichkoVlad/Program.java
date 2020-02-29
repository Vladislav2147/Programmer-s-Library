//TODO
// Вызывать его методы в менеджерах
// Поиск, возвращение списка объектов из бд, readermanager
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.BookTableManager;
import com.ShichkoVlad.DatabaseManagers.MyConnector;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;
import com.ShichkoVlad.LibraryManagers.BookManager;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Program {
    public static void main (String[] args) {



        try(Connection connect = MyConnector.getConnection()) {
            BookManager bookManager = new BookManager();
            bookManager.takeBookFromReader(1, connect);
        } catch(SQLException | AmbiguousFilterException e) {

            System.out.println(e);
        }
    }
}
