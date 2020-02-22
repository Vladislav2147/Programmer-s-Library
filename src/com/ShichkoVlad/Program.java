//TODO
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

        //bookManager.addNewBook(new Book("The first book", Year.of(1997)));
        //bookManager.addNewBook(new Book("The second book", Year.of(1999)));

        //bookManager.writeListToFile("books.bin");
        bookManager.getListFromFile("books.bin");

        System.out.println("\nСписок книг, полученный из файла:");
        for(Book book: library.getBooks()) {
            System.out.println(book);
        }

        ReaderManager readerManager = new ReaderManager(library);

        //readerManager.addNewReader(new Reader("Vlad", "Shichko", "vandl3511@gmail.com"));
        //readerManager.addNewReader(new Reader("Name", "Surname", "noname@gmail.com"));

        //readerManager.writeListToFile("readers.bin");
        readerManager.getListFromFile("readers.bin");

        System.out.println("\nСписок читателей, полученный из файла:");
        for(Reader reader: library.getReaders()) {
            System.out.println(reader);
        }

        try {
            System.out.println("\nКнига с названием \"The first book\":");
            System.out.println(bookManager.findByBookName("The first book"));
        }
        catch (NoSuchBookException e) {
            System.out.println(e.getMessage());
        }

        try {
            Reader myReader = readerManager.findByEmail("vandl3511@gmail.com");
            Book myBook = library.getBooks().get(0);
            bookManager.giveBookToReader(myBook, myReader);

            System.out.println("\nВыдали читателю книгу: ");
            System.out.println(myReader);

            System.out.println("\nПытаемся выдать ту же книгу другому читателю -> получаем исключение: ");
            bookManager.giveBookToReader(myBook, library.getReaders().get(1));

        }
        catch (NoSuchReaderException | NoSuchBookException | ReaderAlreadyHasBookException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("\nВозвращаем книгу:");

            Reader myReader = readerManager.findByEmail("vandl3511@gmail.com");
            bookManager.takeBookFromReader(myReader);

            System.out.println("Книга: " + myReader.getBook());

            System.out.println("\nУдаляем читателя и выводим список читателей: ");
            readerManager.removeReader(myReader);

            for(Reader reader: library.getReaders()) {
                System.out.println(reader);
            }
        }
        catch (NoSuchReaderException | NoSuchBookException e) {
            System.out.println(e.getMessage());
        }

    }
}
