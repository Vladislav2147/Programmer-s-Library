//TODO
// Снести коллекции + библиотеку, менджеров
// Добавить менеджер работы с бд (добавить, удалить, поиск, редактировать). В метод передается объект и имя таблицы, возвращает успешность операции
// Вызывать его методы в менеджерах
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Country;
import com.ShichkoVlad.Book.Gender;
import com.ShichkoVlad.Book.Publisher;
import com.ShichkoVlad.DatabaseManagers.DatabaseAuthorManager;
import com.ShichkoVlad.DatabaseManagers.DatabasePublisherManager;
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

            //Publisher publisher = new Publisher(4, "some publisher", Country.Belarus, "8 lenina st., Minsk", "220001", "some@tut.by");
            DatabasePublisherManager publisherManager = new DatabasePublisherManager();
            //publisherManager.addToTable(publisher, connect);
            System.out.println(publisherManager
                    .getInstanceById(publisherManager.getIdByFilter("name = 'some publisher'", connect), connect));
            publisherManager.changeInTable(4, "name = 'some_publisher'", connect);

        } catch(SQLException | AmbiguousFilterException e) {

            System.out.println(e);
        }
    }
}
