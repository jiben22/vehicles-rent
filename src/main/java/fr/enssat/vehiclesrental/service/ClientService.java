package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.enums.Position;
import fr.enssat.vehiclesrental.repository.EmployeeRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.EmployeeAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
