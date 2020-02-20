package com.ShichkoVlad.Library;

import com.ShichkoVlad.Book.Book;
import com.ShichkoVlad.Reader.Reader;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Library {
    List<Reader> readers;
    List<Book> books;

    public Library () {
        readers = new ArrayList<>();
        books = new ArrayList<>();
    }

    //геттеры возвращают немодифицируемые списки во избежание изменения списков извне пакета через геттер
    public List<Reader> getReaders() {
        return Collections.unmodifiableList(readers);
    }
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }
}
