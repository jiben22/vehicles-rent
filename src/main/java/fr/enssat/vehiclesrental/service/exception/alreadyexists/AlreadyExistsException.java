package fr.enssat.vehiclesrental.service.exception.alreadyexists;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String msg) {
        super(msg+" already exists !");
    }
}
