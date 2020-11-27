package fr.enssat.vehiclesrental.service.exception.notfound;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg+" not found !");
    }
}
