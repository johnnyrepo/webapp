package ee.srini.webapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Country {

    @Id
    private Long id;
    private String isoCode;
    private String name;

}
