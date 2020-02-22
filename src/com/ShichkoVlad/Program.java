//TODO
// файлы
// логгер
package com.ShichkoVlad;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.Library.BookManager;
import com.ShichkoVlad.Library.Library;
import com.ShichkoVlad.Library.ReaderManager;
import com.ShichkoVlad.Reader.Reader;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Program {
    public static void main (String[] args) {
        Library library = new Library();
        BookManager bookManager = new BookManager(library);
        bookManager.addNewBook(new Book("Title", Year.of(2001)));
        bookManager.addNewBook(new Book("book", Year.of(2005)));

        ReaderManager readerManager = new ReaderManager(library);
        readerManager.addNewReader(new Reader("Vlad", "Shichko", "vandl3511@gmail.com"));
        readerManager.addNewReader(new Reader("Name", "Surname", "noname@gmail.com"));

        for(Book book: library.getBooks()) {
            System.out.println(book);
        }

        try {
            Reader reader = new Reader("Vlad", "Shichko", "vandl3511@gmail.com");
            bookManager.giveBookToReader(library.getBooks().get(0), reader);
            readerManager.addNewReader(reader);
        }
        catch (ReaderAlreadyHasBookException | NoSuchBookException | NoSuchReaderException e) {
            System.out.println(e.getMessage());
        }

        for(Reader reader: library.getReaders()) {
            System.out.println(reader);
        }



    }
}
