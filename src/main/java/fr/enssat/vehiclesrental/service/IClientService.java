package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IClientService extends UserDetailsService {
    boolean exists(long id);
    List<Client> searchClients(String firstname, String lastname, String email, String zipcode);
    boolean addClient(Client client);

}
