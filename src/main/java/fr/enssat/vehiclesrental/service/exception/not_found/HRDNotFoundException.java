package fr.enssat.vehiclesrental.service.exception.not_found;

public class HRDNotFoundException extends NotFoundException {
    public HRDNotFoundException(String msg) {
        super("HRD "+msg);
    }
}
