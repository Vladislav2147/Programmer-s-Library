package com.ShichkoVlad.Book;

import com.ShichkoVlad.JsonSerialization.LocalDateSerialization.LocalDateDeserializer;
import com.ShichkoVlad.JsonSerialization.LocalDateSerialization.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private int id;
    private String name;

    @JsonSerialize(using = YearSerializer.class)
    @JsonDeserialize(using = YearDeserializer.class)
    private Year year;
    private Publisher publisher;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate publishingDate;
    private Integer copiesAmount;
    private List<Author> authors = new ArrayList<>();
    private List<byte[]> photos = new ArrayList<>();

    //Конструктор обязательных полей
    public Book(int id, String name, Year year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}
