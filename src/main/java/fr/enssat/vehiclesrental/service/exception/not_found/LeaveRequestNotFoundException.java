package fr.enssat.vehiclesrental.service.exception.not_found;

public class LeaveRequestNotFoundException extends NotFoundException {

    public LeaveRequestNotFoundException(String id) {
        super("Leave request : " + id);
    }
}
