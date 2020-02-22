package com.ShichkoVlad.Reader;

import com.ShichkoVlad.Book.Book;
import lombok.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Optional;

@Data
@NoArgsConstructor
public class Reader implements Serializable {
    private String firstName;
    private String secondName;
    private Year birthYear;
    private String email;
    private String phone;
    private byte[] photo;
    private Optional<Book> book = Optional.empty();

    //Конструктор обязательных полей
    public Reader(String firstName, String secondName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    //Конструктор без Book, добавление/удаление книги через BookManager
    public Reader(String firstName, String secondName, Year birthYear, String email, String phone, byte[] photo) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthYear = birthYear;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }
}
