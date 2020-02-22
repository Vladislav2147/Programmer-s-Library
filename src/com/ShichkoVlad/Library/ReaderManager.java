package com.ShichkoVlad.Library;

import com.ShichkoVlad.Exceptions.NoSuchReaderException;
import com.ShichkoVlad.Reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

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

}
