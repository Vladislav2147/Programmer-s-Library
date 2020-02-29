//TODO
// Снести коллекции + библиотеку, менджеров
// Добавить менеджер работы с бд (добавить, удалить, поиск, редактировать). В метод передается объект и имя таблицы, возвращает успешность операции
// Вызывать его методы в менеджерах
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.DatabaseManagers.BookTableManager;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Program {
    public static void main (String[] args) {

        ResourceBundle resource = ResourceBundle.getBundle("db", Locale.getDefault());
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

        try(Connection connect = DriverManager.getConnection(url, user, pass)) {
            BookTableManager manager = new BookTableManager();
            Book book = manager.getInstanceById(1, connect);
            manager.removeFromTable(1, connect);
            manager.addToTable(book, connect);
        } catch(SQLException | AmbiguousFilterException e) {

            System.out.println(e);
        }
    }
}
