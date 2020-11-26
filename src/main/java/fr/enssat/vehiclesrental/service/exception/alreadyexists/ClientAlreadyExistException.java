package fr.enssat.vehiclesrental.service.exception.alreadyexists;

import fr.enssat.vehiclesrental.model.Client;

public class ClientAlreadyExistException extends AlreadyExistsException {
    public ClientAlreadyExistException(Client client) { super(client.toString()); }
}
