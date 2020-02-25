package com.ShichkoVlad.Book;

import com.ShichkoVlad.LibrarySerializaton.LocalDate.LocalDateDeserializer;
import com.ShichkoVlad.LibrarySerializaton.LocalDate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private String name;
    private Year year;
    private Publisher publisher;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate publishingDate;
    private Integer copiesAmount;
    private List<Author> authors = new ArrayList<>();
    private List<byte[]> photos = new ArrayList<>();

    //Конструктор обязательных полей
    public Book(String name, Year year) {
        this.name = name;
        this.year = year;
    }
}
