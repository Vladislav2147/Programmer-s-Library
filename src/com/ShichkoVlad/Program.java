package com.ShichkoVlad;

import com.ShichkoVlad.Book.*;
import com.ShichkoVlad.Exceptions.*;
import com.ShichkoVlad.Library.*;
import com.ShichkoVlad.Reader.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Program {
    public static void main (String[] args) {

        PropertyConfigurator.configure("resources/log4j.properties");
        Logger logger = Logger.getLogger(Program.class);

        Publisher publisher = new Publisher("Publisher", Country.Belarus,
                "Minsk, lenina 15", "220000", "belstu@belstu.by");
        Author author = new Author("Ivan", "Ivanov", Year.of(1972), Gender.Male, null);
        Author author2 = new Author("Elithabeth", "Ivanova", Year.of(1965), Gender.Female, null);
        List<Author> authors  = new ArrayList<>();
        authors.add(author);
        authors.add(author2);
        Book book1 = new Book("First Book", Year.of(1995), publisher,
                LocalDate.of(1995, 05, 21), 5000, authors, null);
        Book book2 = new Book("second Book", Year.of(2000));

        Reader reader1 = new Reader("Ivan", "Van", "email@gmail.com");
        Reader reader2 = new Reader("Reader", "Readerov", "someemail@mail.ru");

        Library library = new Library();

        ReaderManager readerManager = new ReaderManager(library);
        BookManager bookManager = new BookManager(library);

        readerManager.addNewReader(reader1);
        readerManager.addNewReader(reader2);
        bookManager.addNewBook(book1);
        bookManager.addNewBook(book2);




    }
}
