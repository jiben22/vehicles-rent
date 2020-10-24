package fr.enssat.vehiclesrental.service.exception.already_exists;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String msg) {
        super(msg+" already exists !");
    }
}
