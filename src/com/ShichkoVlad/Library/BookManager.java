package com.ShichkoVlad.Library;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class BookManager {
    Library library;

    public void addNewBook(Book book) {
        library.books.add(book);
    }

    public void changeBook(Book book) {
        if (library.books.contains(book)) {
            book.setName("new name");
            book.setCopiesAmount(8);
        }
        else {
            throw new NoSuchBookException("There is no book");
        }
    }

    public void removeBook(Book book) {
        if (library.books.contains(book)) {
            library.books.remove(book);
        }
        else {
            throw new NoSuchBookException("There is no book");
        }
    }


    //Методы поиска книг по различным параметрам
    public List<Book> findByBookName(String title) {
        if (library.books.stream().anyMatch(book -> book.getName().equals(title))) {

            return library.books.stream()
                    .filter(book -> book.getName().equals(title))
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }
    public List<Book> findByYear(Year begin, Year end) {
        if (library.books.stream().anyMatch(book -> book.getYear().getValue() >= begin.getValue()
                && book.getYear().getValue() <= end.getValue())) {

            return library.books.stream()
                    .filter(book -> book.getYear().getValue() >= begin.getValue()
                            && book.getYear().getValue() <= end.getValue())
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }
    public List<Book> findByAuthorName(String authorName) {
        if (library.books.stream()
                .anyMatch(book -> book.getAuthors().stream()
                        .anyMatch(author -> authorName.contains(author.getFirstName()) &&
                                authorName.contains(author.getSecondName())))) {

            return library.books.stream()
                    .filter(book -> book.getAuthors().stream()
                            .anyMatch(author -> authorName.contains(author.getFirstName()) &&
                                    authorName.contains(author.getSecondName())))
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }
}
