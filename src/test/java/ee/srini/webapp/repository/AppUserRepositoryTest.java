package ee.srini.webapp.repository;

import ee.srini.webapp.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void testFindAppUserByUserNameAndPassword() throws NoSuchAlgorithmException {
        String hashedPass = hashPassword("admin");
        AppUser au = appUserRepository.findByUsernameAndPassword("admin", hashedPass);

        assertNotNull(au);
        assertEquals("admin", au.getUsername());
        assertEquals(hashedPass, au.getPassword());
        assertEquals(1, au.getId());
    }

    @Test
    public void testFindAppUserByUserNameAndPasswordFails() throws NoSuchAlgorithmException {
        String hashedPass = hashPassword("wrong.pass");
        AppUser au = appUserRepository.findByUsernameAndPassword("admin", hashedPass);

        assertNull(au);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] sha256 = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : sha256) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
