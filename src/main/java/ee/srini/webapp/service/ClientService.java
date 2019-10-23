package ee.srini.webapp.service;

import ee.srini.webapp.model.Client;
import ee.srini.webapp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClientsByAppUserId(Long appUserId) {
        return clientRepository.findByAppUserId(appUserId);
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public Client getClientByIdAndAppUserId(Long id, Long appUserId) {
        return clientRepository.findByIdAndAppUserId( id, appUserId);
    }

    public Client updateClient(Client client, Long appUserId) {
        client.setAppUserId(appUserId);

        return clientRepository.save(client);
    }

    public Client addClient(Client client, Long appUserId) {
        client.setAppUserId(appUserId);

        return clientRepository.save(client);
    }
}
