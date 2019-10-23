package ee.srini.webapp.service;

import ee.srini.webapp.model.AppUser;
import ee.srini.webapp.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public Long login(String username, String password) {
        AppUser appUser = null;
        try {
            appUser = appUserRepository.findByUsernameAndPassword(username, hashPassword(password));
        } catch (NoSuchAlgorithmException e) {
            log.error("Failed to login", e);
        }

        return appUser == null ? null : appUser.getId();
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
