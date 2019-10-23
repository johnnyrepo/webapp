package ee.srini.webapp.repository;

import ee.srini.webapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByAppUserId(Long appUserId);

    Client findByIdAndAppUserId(Long id, Long appUserId);

}
