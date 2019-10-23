package ee.srini.webapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Optional;

@Data
@Entity
@SequenceGenerator(name = "CLIENT_SEQ", allocationSize = 1)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SEQ")
    private Long id;
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    private String firstName;
    @Size(min = 1, max = 200, message = "Last name must be between 1 and 200 characters")
    private String lastName;
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;
    @Email(message = "Email should be valid")
    @Size(max = 200, message = "Email should be less than 200 characters or left empty")
    private String email;
    @Size(min = 5, max = 500, message = "Address must be between 5 and 500 characters")
    private String address;
    @NotNull(message = "Country has to be selected")
    private Long countryId;
    private Long appUserId;

}
