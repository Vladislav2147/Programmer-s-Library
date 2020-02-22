package com.ShichkoVlad.Library;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.List;

@AllArgsConstructor
@Data
public class ReaderManager {
    Library library;

    public boolean addNewReader(Reader reader) {
        if(!library.readers.contains(reader)) {
            library.readers.add(reader);
            return true;
        }
        return false;
    }
    public void changeReader(Reader reader) throws NoSuchReaderException {
        if(library.readers.contains(reader)) {
            reader.setFirstName("new first name");
        }
        else {
            throw new NoSuchReaderException("There is no such reader");
        }
    }
    public void removeReader(Reader reader) throws NoSuchReaderException {
        if(library.readers.contains(reader)) {
            library.readers.remove(reader);
        }
        else {
            throw new NoSuchReaderException("There is no such reader");
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean getListFromFile(String path) {
        File f = new File(path);
        try ( FileInputStream fis = new FileInputStream(f)){
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            library.readers = (List<Reader>) inputStream.readObject();
            return true;
        }
        catch ( IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
