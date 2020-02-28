//TODO
// Снести коллекции + библиотеку, менджеров
// Возможно классы для работы с каждой таблицей (типо writers) и/или обобщение
// Добавить менеджер работы с бд (добавить, удалить, поиск, редактировать). В метод передается объект и имя таблицы, возвращает успешность операции
// Редактирование: выполнить поиск по имени + заменить на новый объект (или update)
// Вызывать его методы в менеджерах
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Book.Country;
import com.ShichkoVlad.DatabaseManager.DatabaseAuthor;
import com.ShichkoVlad.Exceptions.*;
import com.ShichkoVlad.Library.*;
import com.ShichkoVlad.Reader.Reader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
            DatabaseAuthor databaseAuthor = new DatabaseAuthor();
            System.out.println(databaseAuthor.getIdByFilter("second_name = 'pushkin'", connect));
        } catch(SQLException | AmbiguousFilterException e) {
            System.out.println(e);
        }
    }
}
