package com.ShichkoVlad.Book;

import lombok.*;

import java.io.Serializable;
import java.time.Year;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author implements Serializable {
    private int id;
    private String firstName;
    private String secondName;
    private Year birthYear;
    private Gender gender;
    private String note;
}
