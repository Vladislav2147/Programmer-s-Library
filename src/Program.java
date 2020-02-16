import Reader.Reader;

public class Program {
    public static void main (String[] args) {
        Reader reader = new Reader("Vlad", "Shichko",
                2001, "vandl3511@gmail.com", "+375297729144");
        System.out.println(reader);
    }
}
