package ee.srini.webapp.repository;

import ee.srini.webapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsernameAndPassword(String username, String password);

}
