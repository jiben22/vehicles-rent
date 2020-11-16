package fr.enssat.vehiclesrental.service.exception.alreadyexists;

import fr.enssat.vehiclesrental.model.Vehicle;

public class VehicleAlreadyExistException extends AlreadyExistsException {
    public VehicleAlreadyExistException(Vehicle vehicle) {
        super(vehicle.toString());
    }
}
