package Book;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Book {
    private String name;
    private Year year;
    private Publisher publisher;
    private Date publishingDate;
    private int copiesAmount;
    private List<Author> authors;

    public Book() {

    }

    public Book(String name, Year year, Publisher publisher, Date publishingDate, int copiesAmount, List<Author> authors) {
        this.name = name;
        this.year = year;
        this.publisher = publisher;
        this.publishingDate = publishingDate;
        this.copiesAmount = copiesAmount;
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                copiesAmount == book.copiesAmount &&
                Objects.equals(name, book.name) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publishingDate, book.publishingDate) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", publisher=" + publisher +
                ", publishingDate=" + publishingDate +
                ", copiesAmount=" + copiesAmount +
                ", authors=" + authors +
                '}';
    }
}
