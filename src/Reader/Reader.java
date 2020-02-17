package Reader;

import Book.Book;
import lombok.*;
import java.io.Serializable;
import java.time.Year;

@Data
@NoArgsConstructor
public class Reader implements Serializable {
    private String firstName;
    private String secondName;
    private Year birthYear;
    private String email;
    private String phone;
    private Book book;

    //Конструктор без Book
    public Reader(String firstName, String secondName, Year birthYear, String email, String phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthYear = birthYear;
        this.email = email;
        this.phone = phone;
    }
}
