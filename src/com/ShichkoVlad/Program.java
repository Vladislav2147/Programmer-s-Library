//TODO
// файлы
// логгер
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.*;
import com.ShichkoVlad.Library.*;
import com.ShichkoVlad.Reader.Reader;

import java.time.Year;

public class Program {
    public static void main (String[] args) {
        Library library = new Library();
        BookManager bookManager = new BookManager(library);
        bookManager.addNewBook(new Book("The first book", Year.of(1997)));
        bookManager.addNewBook(new Book("The second book", Year.of(1999)));
        bookManager.writeListToFile("books.bin");
        ReaderManager readerManager = new ReaderManager(library);
        readerManager.addNewReader(new Reader("Vlad", "Shichko", "vandl3511@gmail.com"));
        readerManager.addNewReader(new Reader("Name", "Surname", "noname@gmail.com"));
        readerManager.writeListToFile("readers.bin");
        for(Book book: library.getBooks()) {
            System.out.println(book);
        }




    }
}
