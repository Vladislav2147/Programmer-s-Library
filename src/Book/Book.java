package Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private Year year;
    private Publisher publisher;
    private Date publishingDate;
    private int copiesAmount;
    private List<Author> authors;
}
