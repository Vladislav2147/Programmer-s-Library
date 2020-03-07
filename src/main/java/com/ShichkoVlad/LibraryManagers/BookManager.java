package com.ShichkoVlad.LibraryManagers;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.NoSuchBookException;
import com.ShichkoVlad.Exceptions.ReaderAlreadyHasBookException;
import com.ShichkoVlad.DatabaseManagers.TableManagers.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
public class BookManager implements ILibraryManager<Book>{

    final static Logger logger = Logger.getLogger(BookManager.class);

    @Override
    public void addNew(Book book, Connection connection) throws SQLException {

        if(book.getPublisher() != null) {
            PublisherTableManager publisherTableManager = new PublisherTableManager();
            publisherTableManager.addToTable(book.getPublisher(), connection);
        }

        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.addToTable(book, connection);

        AuthorTableManager authorTableManager = new AuthorTableManager();
        for(Author author: book.getAuthors()) {
            try {
                authorTableManager.addToTable(author, connection);
                WrittenByTableManager.setAuthorOfBook(book.getId(), author.getId(), connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        logger.info("new book " + book.getId() + " was added");

    }

    @Override
    public Book getById(int bookId, Connection connection) throws SQLException {

        BookTableManager bookTableManager = new BookTableManager();
        Book book = bookTableManager.getInstanceById(bookId, connection);

        logger.info("Book " + book.getId() + " was received from database");

        return book;
    }

    @Override
    public void changeItem(int bookId, Connection connection) throws SQLException {

        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.changeInTable(bookId, "title", "new title", connection);

        logger.info("Book " + bookId + " was changed");

    }

    @Override
    public void remove(int bookId, Connection connection) throws SQLException {

        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.removeFromTable(bookId, connection);

        logger.info("Book " + bookId + " was changed");

    }

    //Выдача книги читателю
    public void giveBookToReader(int bookId, int readerId, Connection connection) throws SQLException, ReaderAlreadyHasBookException {

        ReaderTableManager readerTableManager = new ReaderTableManager();

        if (readerTableManager.getRowById(readerId, connection).getObject("book_id") != null) {

            throw new ReaderAlreadyHasBookException("reader " + readerId + " already has another book!");

        }
        else {

            readerTableManager.changeInTable(readerId, "book_id", bookId, connection);

            logger.info("Book " + bookId + " was given to reader " + readerId);

        }
    }

    //Забрать книгу у читателя
    public void takeBookFromReader(int readerId, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.takeBookFromReader(readerId, connection);

        logger.info("Book was taken from reader " + readerId);

    }

    //Методы поиска по книгам
    public List<Book> find(Predicate<Book> bookPredicate, Connection connection) throws NoSuchBookException, SQLException {

        List<Book> books = getListFromDatabase(connection)
                .stream()
                .filter(bookPredicate)
                .collect(Collectors.toList());

        if (books.size() > 0) {

            logger.info("Books were found");

            return books;

        }
        else {

            throw new NoSuchBookException("No books found");

        }

    }

    public List<Book> findByBookName(String title, Connection connection) throws NoSuchBookException, SQLException {

        return find(book -> book.getName().equals(title), connection);

    }

    public List<Book> findByYear(Year begin, Year end, Connection connection) throws NoSuchBookException, SQLException {

        return find(book -> book.getYear().getValue() >= begin.getValue() &&
                book.getYear().getValue() <= end.getValue(), connection);

    }

    public List<Book> findByAuthorName(String authorName, Connection connection) throws NoSuchBookException, SQLException {

        return find(book -> book.getAuthors().stream().anyMatch(author -> authorName.contains(author.getFirstName()) &&
                authorName.contains(author.getSecondName())), connection);

    }

    //Возвращает список всех книг из базы данных
    @Override
    public List<Book> getListFromDatabase(Connection connection) throws SQLException {

        BookTableManager bookTableManager = new BookTableManager();
        List<Book> books = bookTableManager.getListFromTable(connection);

        StringBuilder logString = new StringBuilder("Books ");
        for(Book book: books) {
            logString.append(book.getId() + " ");
        }
        logString.append(" were received from database");
        logger.info(logString);

        return books;

    }

    //Записывает список книг в базу данных
    @Override
    public void setListToDatabase(List<Book> books, Connection connection) throws SQLException {

        BookTableManager bookTableManager = new BookTableManager();

        for(Book book: books) {
            bookTableManager.addToTable(book, connection);
            logger.info("Book " + book.getId() + " was added to database");
        }

    }

}
