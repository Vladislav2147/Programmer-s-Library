package Book;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Objects;

@Getter
@Setter

public class Author {
    private String firstName;
    private String secondName;
    private Year birthYear;
    private Gender gender;
    private String note;

    public Author(String firstName, String secondName, Year birthYear, Gender gender, String note) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthYear = birthYear;
        this.gender = gender;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return birthYear == author.birthYear &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(secondName, author.secondName) &&
                gender == author.gender &&
                Objects.equals(note, author.note);
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthYear=" + birthYear +
                ", gender=" + gender +
                ", note='" + note + '\'' +
                '}';
    }
}
