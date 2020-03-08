package com.shichko.libraryManagers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//Классы, реализующий данный интерфейс, упрощают работу с базой данных, предоставляя необходимый по заданию функционал
public interface ILibraryManager<T> {

    void addNew(T item, Connection connection) throws SQLException;
    T getById(int id, Connection connection) throws SQLException;
    void changeItem(int id, Connection connection) throws SQLException;
    void remove(int id, Connection connection) throws SQLException;
    List<T> getListFromDatabase(Connection connection) throws SQLException;
    void setListToDatabase(List<T> items, Connection connection) throws SQLException;

}
