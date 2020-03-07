package com.ShichkoVlad.LibraryManagers;

import com.ShichkoVlad.DatabaseManagers.TableManagers.ReaderTableManager;
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
public class ReaderManager implements ILibraryManager<Reader> {

    final static Logger logger = Logger.getLogger(ReaderManager.class);

    @Override
    public void addNew(Reader reader, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.addToTable(reader, connection);
        logger.info("new reader " + reader.getId() + " was added to database");

    }

    @Override
    public Reader getById(int readerId, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        Reader reader = readerTableManager.getInstanceById(readerId, connection);
        logger.info("Reader " + readerId + " was received from database");

        return reader;

    }

    @Override
    public void changeItem(int readerId, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.changeInTable(readerId, "first_name", "new_name", connection);
        logger.info("reader " + readerId + " was changed");

    }

    @Override
    public void remove(int readerId, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();
        readerTableManager.removeFromTable(readerId, connection);
        logger.info("reader " + readerId + " was removed from database");

    }

    public Reader findByEmail(String email, Connection connection) throws NoSuchReaderException, SQLException {

        if (getListFromDatabase(connection).stream().anyMatch(reader -> reader.getEmail().equals(email))) {

            Reader resultReader = getListFromDatabase(connection).stream()
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

    @Override
    public List<Reader> getListFromDatabase(Connection connection) throws SQLException {

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
    @Override
    public void setListToDatabase(List<Reader> readers, Connection connection) throws SQLException {

        ReaderTableManager readerTableManager = new ReaderTableManager();

        for(Reader reader: readers) {
            readerTableManager.addToTable(reader, connection);
            logger.info("Reader " + reader.getId() + " was added to database");
        }

    }
}
