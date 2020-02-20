package com.ShichkoVlad.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private Year year;
    private Publisher publisher;
    private Calendar publishingDate;
    private int copiesAmount;
    private List<Author> authors;
    private List<byte[]> photos;
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.YYYY");
        return "com.ShichkoVlad.Book{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", publisher=" + publisher +
                ", publishingDate=" + dateFormat.format(publishingDate.getTime()) +
                ", copiesAmount=" + copiesAmount +
                ", authors=" + authors +
                '}';
    }
}
