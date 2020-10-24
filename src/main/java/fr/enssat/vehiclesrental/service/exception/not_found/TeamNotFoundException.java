package fr.enssat.vehiclesrental.service.exception.not_found;

public class TeamNotFoundException extends NotFoundException {
    public TeamNotFoundException(String msg) {
        super("Team "+msg);
    }
}
