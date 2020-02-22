package com.ShichkoVlad.Library;

import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Data
public class ReaderManager {

    final static Logger logger = Logger.getLogger(ReaderManager.class);

    Library library;

    public boolean addNewReader(Reader reader) {

        if(!library.readers.contains(reader)) {
            library.readers.add(reader);
            logger.info("new reader '" + reader.getEmail() + "' was added");
            return true;
        }
        logger.info("new reader '" + reader.getEmail() + "' weren't added");
        return false;

    }

    public void changeReader(Reader reader) throws NoSuchReaderException {

        if(library.readers.contains(reader)) {
            reader.setFirstName("new first name"); //TODO исправить в дальнейшем
            logger.info("reader '" + reader.getEmail() + "' was changed");
        }
        else {
            throw new NoSuchReaderException("There is no such reader");
        }
    }

    public void removeReader(Reader reader) throws NoSuchReaderException {

        if(library.readers.contains(reader)) {
            library.readers.remove(reader);
            logger.info("reader '" + reader.getEmail() + "' was removed");
        }
        else {
            throw new NoSuchReaderException("There is no such reader");
        }

    }

    public Reader findByEmail(String email) throws NoSuchReaderException {

        if (library.readers.stream().anyMatch(reader -> reader.getEmail().equals(email))) {
            logger.info("reader  '" + email + "' was found by email");
            return library.readers.stream()
                    .filter(reader -> reader.getEmail().equals(email))
                    .findFirst()
                    .get();
        }
        else {
            throw new NoSuchReaderException("There is no reader with email " + email);
        }

    }

    public boolean writeListToFile(String path) {

        boolean flag = false;
        File file = new File(path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            if (fileOutputStream != null) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(library.readers);
                flag = true;
                logger.info("readers were written to the file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return flag;

    }

    public boolean getListFromFile(String path) {

        File f = new File(path);
        try ( FileInputStream fis = new FileInputStream(f)){
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            library.readers = (List<Reader>) inputStream.readObject();

            //Book поле transient; чтобы избежать NullPointerException, инициализируем явно
            library.readers.stream().forEach(reader -> reader.setBook(Optional.empty()));
            logger.info("readers were received from the file");
            return true;
        }
        catch ( IOException | ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return false;

    }
}
