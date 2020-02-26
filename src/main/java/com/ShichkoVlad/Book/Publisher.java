package com.ShichkoVlad.Book;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher implements Serializable {
    private String name;
    private Country country;
    private String address;
    private String postCode;
    private String email;

}
