package fr.enssat.vehiclesrental.service.exception.not_found;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg+" not found !");
    }
}
