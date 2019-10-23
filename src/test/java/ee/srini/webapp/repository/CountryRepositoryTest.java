package ee.srini.webapp.repository;

import ee.srini.webapp.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testFindAllCountries() {
        List<Country> countries = countryRepository.findAll();

        assertEquals(5, countries.size());
    }

}
