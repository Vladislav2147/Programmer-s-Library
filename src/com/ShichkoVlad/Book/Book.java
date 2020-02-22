package com.ShichkoVlad.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private Year year;
    private Publisher publisher;
    private Calendar publishingDate;
    private Integer copiesAmount;
    private List<Author> authors = new ArrayList<>();
    private List<byte[]> photos = new ArrayList<>();

    //Конструктор обязательных полей
    public Book(String name, Year year) {
        this.name = name;
        this.year = year;
    }
}
