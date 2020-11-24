package fr.enssat.vehiclesrental.service.exception.notfound;

public class ClientNotFoundException extends NotFoundException {
    public ClientNotFoundException(String msg) {
        super("Client "+msg);
    }
}
