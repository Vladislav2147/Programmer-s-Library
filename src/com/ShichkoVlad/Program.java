//TODO
// Передавать в метод поиска предикат, по которому осуществляется поиск (или нет)
// Нормальные конструкторы для минимального набора параметров + проверка на null обязательных
// доделать функционал менеджеров
// Сделать все не необходимые поля optional
// файлы
// логгер
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Library.BookManager;
import com.ShichkoVlad.Library.Library;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main (String[] args) {
        Library library = new Library();
        BookManager bookManager = new BookManager(library);
        List<Author> authors = new ArrayList<>();
        //authors.add(new Author("vladec", "shichko", null, null,null));
        bookManager.addNewBook(new Book("name", Year.of(2001), null, null, null, null , null));
        bookManager.addNewBook(new Book("noname", Year.of(2005), null, null, null, null, null));
        System.out.println(bookManager.getLibrary().getBooks().size());
        try {
            bookManager.removeBook(bookManager.getLibrary().getBooks().get(0));
        }
        catch (NoSuchBookException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookManager.getLibrary().getBooks().size());
    }
}
