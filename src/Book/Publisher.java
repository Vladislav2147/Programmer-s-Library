package Book;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Setter
@Getter
public class Publisher {
    private String name;
    private Country country;
    private String address;
    private String postCode;
    private String email;

    public Publisher() {

    }
    public Publisher(String name, Country country, String address, String postCode, String email) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.postCode = postCode;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(name, publisher.name) &&
                country == publisher.country &&
                Objects.equals(address, publisher.address) &&
                Objects.equals(postCode, publisher.postCode) &&
                Objects.equals(email, publisher.email);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", country=" + country +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
