package com.ShichkoVlad.Library;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Reader.Reader;
import lombok.Data;

import java.util.*;

@Data
public class Library {
    Set<Reader> readers;
    Set<Book> books;

    public Library () {
        readers = new HashSet<>();
        books = new HashSet<>();
    }

    //геттеры возвращают немодифицируемые списки во избежание изменения списков извне пакета через геттер
    public Set<Reader> getReaders() {
        return Collections.unmodifiableSet(readers);
    }
    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }
}
