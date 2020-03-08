package com.shichko.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher implements Serializable {
    private int id;
    private String name;
    private Country country;
    private String address;
    private String postCode;
    private String email;

}
