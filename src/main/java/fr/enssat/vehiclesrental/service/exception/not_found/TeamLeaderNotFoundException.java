package fr.enssat.vehiclesrental.service.exception.not_found;

public class TeamLeaderNotFoundException extends NotFoundException {
    public TeamLeaderNotFoundException(String msg) {
        super("TeamLeader "+msg);
    }
}
