package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService {
    @Override
    public boolean exists(long id) {
        return false;
    }

    @Override
    public List<Client> searchClients(String firstname, String lastname, String email, String zipcode) {
        return null;
    }

    @Override
    public boolean addClient(Client client) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
