//TODO
// Снести коллекции + библиотеку, менджеров
// Добавить менеджер работы с бд (добавить, удалить, поиск, редактировать). В метод передается объект и имя таблицы, возвращает успешность операции
// Вызывать его методы в менеджерах
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Gender;
import com.ShichkoVlad.DatabaseManagers.DatabaseAuthorManager;
import com.ShichkoVlad.Exceptions.*;

import java.sql.*;
import java.time.Year;
import java.util.Locale;
import java.util.ResourceBundle;

public class Program {
    public static void main (String[] args) {

        ResourceBundle resource = ResourceBundle.getBundle("db", Locale.getDefault());
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

        try(Connection connect = DriverManager.getConnection(url, user, pass)) {

            DatabaseAuthorManager databaseAuthorManager = new DatabaseAuthorManager();
            Author author = databaseAuthorManager.getInstanceById(2, connect);
            System.out.println(author);
            databaseAuthorManager.changeInTable(3, "second_name = 'no author', birth_year = 1990" ,connect);

        } catch(SQLException | AmbiguousFilterException e) {

            System.out.println(e);
        }
    }
}
