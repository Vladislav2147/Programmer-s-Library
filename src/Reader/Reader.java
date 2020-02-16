package Reader;
import lombok.*;

import java.time.*;


@NoArgsConstructor
@Getter
@Setter
public class Reader {
    private String firstName;
    private String secondName;
    private int birthYear;
    private String email;
    private String phone;

    public Reader(String firstName, String secondName, int birthYear, String email, String phone) {
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
                '}';
    }
}
