package com.ShichkoVlad.Book;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    private String name;
    private Country country;
    private String address;
    private String postCode;
    private String email;

}
