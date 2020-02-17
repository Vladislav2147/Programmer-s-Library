import Book.*;
import Reader.Reader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class Program {
    public static void main (String[] args) {
        Reader reader = new Reader("Vladec", "Shichko",
                Year.of(2001), "vandl3511@gmail.com", "+375297729144");
        File file = new File("readers.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            ObjectOutputStream objStream = new ObjectOutputStream(fileOutputStream);
            objStream.writeObject(reader);
        }
        catch (IOException e) {

        }
    }
}
