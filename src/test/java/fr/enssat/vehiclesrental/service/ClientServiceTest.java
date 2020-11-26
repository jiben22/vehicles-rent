package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.factory.ClientFactory;
import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.repository.ClientRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.ClientAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.ClientNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @DisplayName("Get client with an id")
    @Test
    public void getClient() {
        when(clientRepository.findById(anyLong()))
                .thenReturn(ofNullable(ClientFactory.getClient()));

        Client client = clientService.getClient(9143686793L);
        assertTrue(new ReflectionEquals(ClientFactory.getClient()).matches(client));
    }

    @DisplayName("Get client with an unknown id")
    @Test()
    public void getClientException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> clientService.getClient(-999));
    }

    @DisplayName("Get all clients")
    @Test
    public void getClients() {
        List<Client> allClients = Arrays.asList(
                ClientFactory.getClient(),
                ClientFactory.getClient2());

        when(clientRepository.findAll(Sort.by(Sort.Direction.ASC, "lastname")))
                .thenReturn(allClients);

        List<Client> clients = clientService.getClients();
        AtomicInteger index = new AtomicInteger();
        clients.forEach(client ->
                assertTrue(new ReflectionEquals(allClients.get(index.getAndIncrement())).matches(client))
        );
    }

    @DisplayName("Create a new client")
    @Test
    public void addClient() {
        when(clientRepository.saveAndFlush(any(Client.class))).thenReturn(ClientFactory.getClient());
        when(clientRepository.existsById(anyLong())).thenReturn(false);

        Client client = clientService.addClient(ClientFactory.getClient());
        assertTrue(new ReflectionEquals(ClientFactory.getClient()).matches(client));
    }

    @DisplayName("Create an client who already exists")
    @Test
    public void addExistingClient() {
        when(clientRepository.existsById(anyLong())).thenReturn(true);
        assertThrows(ClientAlreadyExistException.class, () -> clientService.addClient(ClientFactory.getClient()));
    }

    @DisplayName("Update an client")
    @Test
    public void editClient() {
        when(clientRepository.saveAndFlush(any(Client.class))).thenReturn(ClientFactory.getClient());
        when(clientRepository.existsById(anyLong())).thenReturn(true);

        Client client = clientService.editClient(ClientFactory.getClient());
        assertTrue(new ReflectionEquals(ClientFactory.getClient()).matches(client));
    }

    @DisplayName("Update an unknown client")
    @Test
    public void editClientException() {
        when(clientRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(ClientNotFoundException.class, () -> clientService.editClient(ClientFactory.getClient()));
    }

    @DisplayName("Archive a client")
    @Test
    public void archiveVehicle() {
        Client client = ClientFactory.getClient();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.ofNullable(client));
        client.setArchived(true);
        when(clientRepository.saveAndFlush(any(Client.class))).thenReturn(client);

        clientService.archiveClient(69L);
        assertTrue(client.isArchived());
    }
}
