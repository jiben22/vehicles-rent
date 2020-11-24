package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Client;

import java.util.List;

public interface IClientService {
    boolean exists(long id);
    Client getClient(long id);
    List<Client> getClients();
    List<Client> searchClients(String firstname, String lastname, String email, String zipcode);
    Client addClient(Client client);
    Client editClient(Client client);
    void deleteClient(long id);
}
