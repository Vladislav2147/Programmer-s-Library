package com.ShichkoVlad.LibraryManagers;

import com.ShichkoVlad.DatabaseManagers.ReaderTableManager;
import com.ShichkoVlad.Exceptions.AmbiguousFilterException;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@AllArgsConstructor
@Data
public class ReaderManager {

    final static Logger logger = Logger.getLogger(ReaderManager.class);

    public void addNewReader(Reader reader, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.addToTable(reader, connection);
        logger.info("new reader " + reader.getId() + " was added to database");

    }

    public Reader getReaderById(int readerId, Connection connection) throws SQLException, AmbiguousFilterException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        Reader reader = readerTableManager.getInstanceById(readerId, connection);
        logger.info("Reader " + readerId + " was recieved from database");

        return reader;

    }

    public void changeReader(int readerId, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.changeInTable(readerId, "first_name", "new_name", connection);
        logger.info("reader " + readerId + " was changed");

    }

    public void removeReader(int readerId, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.removeFromTable(readerId, connection);
        logger.info("reader " + readerId + " was removed from database");

    }

    public Reader findByEmail(String email, Connection connection) throws NoSuchReaderException, SQLException, AmbiguousFilterException {

        if (getReadersFromDatabase(connection).stream().anyMatch(reader -> reader.getEmail().equals(email))) {

            Reader resultReader = getReadersFromDatabase(connection).stream()
                    .filter(reader -> reader.getEmail().equals(email))
                    .findFirst()
                    .get();
            logger.info("reader  " + resultReader.getId() + " was found by email");
            return resultReader;

        }
        else {

            throw new NoSuchReaderException("There is no reader with email " + email);

        }

    }

    public List<Reader> getReadersFromDatabase(Connection connection) throws SQLException, AmbiguousFilterException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        List<Reader> readers = readerTableManager.getListFromTable(connection);

        StringBuilder logString = new StringBuilder("Readers ");
        for(Reader reader: readers) {
            logString.append(reader.getId() + " ");
        }
        logString.append(" were recieved from database");
        logger.info(logString);

        return readers;

    }

    //Записывает список читателей в базу данных
    public void writeBooksToDatabase(List<Reader> readers, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();

        for(Reader reader: readers) {
            readerTableManager.addToTable(reader, connection);
            logger.info("Reader " + reader.getId() + " was added to database");
        }

    }
}
