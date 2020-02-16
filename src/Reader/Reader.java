package Reader;
import Book.Book;
import lombok.*;

import java.time.Year;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
public class Reader {
    private String firstName;
    private String secondName;
    private Year birthYear;
    private String email;
    private String phone;
    private Book book;

    public Reader(String firstName, String secondName, Year birthYear, String email, String phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthYear = birthYear;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthYear=" + birthYear +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return birthYear == reader.birthYear &&
                firstName.equals(reader.firstName) &&
                secondName.equals(reader.secondName) &&
                email.equals(reader.email) &&
                phone.equals(reader.phone) &&
                Objects.equals(book, reader.book);
    }

}
