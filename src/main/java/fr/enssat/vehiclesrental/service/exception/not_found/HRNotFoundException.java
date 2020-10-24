package fr.enssat.vehiclesrental.service.exception.not_found;

public class HRNotFoundException extends NotFoundException {
    public HRNotFoundException(String msg) {
        super("HR "+msg);
    }
}
