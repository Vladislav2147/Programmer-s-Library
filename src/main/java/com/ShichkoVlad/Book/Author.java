package com.ShichkoVlad.Book;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
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
    @JsonSerialize(using = YearSerializer.class)
    @JsonDeserialize(using = YearDeserializer.class)
    private Year birthYear;
    private Gender gender;
    private String note;

}
