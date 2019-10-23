package ee.srini.webapp.repository;

import ee.srini.webapp.model.Client;
import ee.srini.webapp.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    public void setUp() {
        List<Country> countries = countryRepository.findAll();

        Client client1 = createClient(1L, "address1", 1L, "test1@test.org", "name1", "surname1", "username1");
        Client client2 = createClient(1L, "address2", 2L, "test2@test.org", "name2", "surname2", "username2");
        Client client3 = createClient(2L, "address3", 3L, "test3@test.org", "name3", "surname3", "username3");

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
    }

    @Test
    public void testFindClientsByAppUserId() {
        List<Client> clients = clientRepository.findByAppUserId(1L);

        assertEquals(2, clients.size());

        clients = clientRepository.findByAppUserId(2L);

        assertEquals(1, clients.size());

        clients = clientRepository.findByAppUserId(3L);

        assertEquals(0, clients.size());
    }

    private Client createClient(Long appUserId, String address, Long countryId, String email, String firstName, String lastName, String username) {
        Client client = new Client();

        client.setAppUserId(appUserId);
        client.setAddress(address);
        client.setCountryId(countryId);
        client.setEmail(email);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setUsername(username);

        return client;
    }

}
