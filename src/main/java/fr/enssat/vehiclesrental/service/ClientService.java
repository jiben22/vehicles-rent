package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.repository.ClientRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.ClientAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.ClientNotFoundException;
import fr.enssat.vehiclesrental.service.exception.notfound.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static fr.enssat.vehiclesrental.repository.ClientRepository.*;
import static fr.enssat.vehiclesrental.repository.VehicleBaseRepository.isNotArchived;
import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService {

    private final ClientRepository repository;

    @Override
    public boolean exists(long id) {
        return repository.existsById(id);
    }

    @Override
    public Client getClient(long id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(String.valueOf(id)));
    }

    @Override
    public Client getClientByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ClientNotFoundException(email));
    }

    @Override
    public List<Client> getClients() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    }

    @Override
    public List<Client> searchClients(String firstname, String lastname, String email, String zipcode) {
        Specification<Client> clientSpecification = buildSpecification(firstname, lastname, email, zipcode);
        if (clientSpecification != null) {
            return repository.findAll(clientSpecification);
        } else {
            return getClients();
        }
    }
    private Specification<Client> buildSpecification(String firstname, String lastname, String email, String zipcode) {
        List<Specification<Client>> specifications = new ArrayList<>();
        specifications.add(ClientRepository.isNotArchived());
        if (!firstname.isEmpty()) specifications.add(hasFirstname(firstname));
        if (!lastname.isEmpty()) specifications.add(hasLastname(lastname));
        if (!email.isEmpty()) specifications.add(hasEmail(email));
        if (!zipcode.isEmpty()) specifications.add(hasZipcode(zipcode));

        if (specifications.size() > 0) {
            Specification<Client> employeeSpecification = where(specifications.get(0));
            specifications.remove(0);
            for (Specification<Client> specification: specifications) {
                employeeSpecification = Objects.requireNonNull(employeeSpecification).and(specification);
            }

            return employeeSpecification;
        } else {
            return null;
        }
    }

    @Override
    public Client addClient(Client client) {
        if (repository.existsById(client.getId()))
            throw new ClientAlreadyExistException(client);
        return repository.saveAndFlush(client);
    }

    @Override
    public Client editClient(Client client) {
        if (!repository.existsById(client.getId()))
            throw new ClientNotFoundException(String.valueOf(client.getId()));
        return repository.saveAndFlush(client);
    }

    @Override
    public Client archiveClient(long id) {
        Client client = getClient(id);
        client.setArchived(true);
        return repository.saveAndFlush(client);
    }

}
