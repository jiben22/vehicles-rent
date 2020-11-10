package fr.enssat.vehiclesrental.service.exception.notfound;

public class VehicleNotFoundException extends NotFoundException {
    public VehicleNotFoundException(String msg) {
        super("Vehicle "+msg);
    }
}
