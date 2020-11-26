package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.ClientFactory;
import fr.enssat.vehiclesrental.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static fr.enssat.vehiclesrental.repository.ClientRepository.hasFirstname;
import static fr.enssat.vehiclesrental.repository.ClientRepository.hasZipcode;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.Specification.where;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    /*
        @DisplayName("Get client with an id")
        @Test
        public void findById() {
            Optional<Client> optionalClient = clientRepository.findById(157314099170601L);
            assertTrue(optionalClient.isPresent());
            Client client = optionalClient.get();
            assertTrue(new ReflectionEquals(ClientFactory.getClientResponsableLocation(), "password").matches(client));
        }

    */
    @DisplayName("Get client with an unknown id")
    @Test
    public void findByUnknownId() {
        Optional<Client> optionalClient = clientRepository.findById(-999L);
        assertFalse(optionalClient.isPresent());
    }

    @DisplayName("Get all clients")
    @Test
    public void findAll() {
        List<Client> clients = clientRepository.findAll();
        assertEquals(3, clients.size());
    }

    @DisplayName("Search a client by his zipecode and firstname")
    @Test
    public void searchClient() {
        List<Client> clients = clientRepository.findAll(where(hasZipcode("35288").and(hasFirstname("Tim"))));
        assertEquals(1, clients.size());
        clients.forEach(client ->
                assertTrue(new ReflectionEquals(ClientFactory.getClient(), "bookings").matches(client))
        );
    }

    @DisplayName("Search an unknown client by his zipecode and firstname")
    @Test
    public void searchUnknownClient() {
        List<Client> clients = clientRepository.findAll(where(hasZipcode("35288").and(hasFirstname("Joe"))));
        assertEquals(0, clients.size());
    }

    @DisplayName("Create a new client")
    @Test
    public void saveAndFlush() {
        Client client = ClientFactory.getClient();
        Client addedClient = clientRepository.saveAndFlush(client);
        assertTrue(new ReflectionEquals(ClientFactory.getClient()).matches(addedClient));

    }
}
