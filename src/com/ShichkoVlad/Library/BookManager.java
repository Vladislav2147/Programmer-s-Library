package com.ShichkoVlad.Library;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.Reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class BookManager {
    Library library;

    public boolean addNewBook(Book book) {
        if(!library.books.contains(book)) {
            library.books.add(book);
            return true;
        }
        return false;
    }

    public void changeBook(Book book) throws NoSuchBookException{
        if (library.books.contains(book)) {
            book.setName("new name"); //TODO исправить на поздних стадиях
        }
        else {
            throw new NoSuchBookException("There is no book");
        }
    }

    public void removeBook(Book book) throws NoSuchBookException{
        if (library.books.contains(book)) {
            library.books.remove(book);
        }
        else {
            throw new NoSuchBookException("There is no book");
        }
    }

    //Выдача книги читателю
    public void giveBookToReader(Book book, Reader reader) throws ReaderAlreadyHasBookException, NoSuchReaderException, NoSuchBookException {
        if(!library.readers.contains(reader)) {
            throw new NoSuchReaderException("There is no such reader");
        }

        if(!library.books.contains(book)) {
            throw new NoSuchBookException("There is no such book");
        }

        if (library.readers.stream().noneMatch(reader1 -> reader1.getBook().orElse(new Book()).equals(book))) {
            if (reader.getBook().isEmpty()) {
                reader.setBook(Optional.of(book));
            }
            else {
                throw new ReaderAlreadyHasBookException("this reader already has a book");
            }
        }
        else {
            throw new ReaderAlreadyHasBookException("other reader already has this book");
        }
    }

    //Забрать книгу у читателя
    public void takeBookFromReader(Reader reader) throws NoSuchReaderException, NoSuchBookException{
        if (!library.readers.contains(reader)) {
            throw new NoSuchReaderException("There is no such reader");
        }

        if (reader.getBook().isPresent()) {
            reader.setBook(Optional.empty());
        }
        else {
            throw new NoSuchBookException("Reader hasn't a book");
        }
    }

    //Методы поиска книг по различным параметрам
    public List<Book> find(Predicate<Book> bookPredicate) throws NoSuchBookException{
        List<Book> books = library.books.stream()
                .filter(bookPredicate)
                .collect(Collectors.toList());
        if (books.size() > 0) {
            return books;
        }
        else {
            throw new NoSuchBookException("No books found");
        }
    }

    public List<Book> findByBookName(String title) throws NoSuchBookException {
        return find(book -> book.getName().equals(title));
    }

    public List<Book> findByYear(Year begin, Year end) throws NoSuchBookException {
        return find(book -> book.getYear().getValue() >= begin.getValue() &&
                            book.getYear().getValue() <= end.getValue());
    }

    public List<Book> findByAuthorName(String authorName) throws NoSuchBookException {
        return find(book -> book.getAuthors().stream().anyMatch(author -> authorName.contains(author.getFirstName()) &&
                authorName.contains(author.getSecondName())));
    }
}
