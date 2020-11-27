package fr.enssat.vehiclesrental.service.exception.alreadybook;

import fr.enssat.vehiclesrental.model.Vehicle;

/**
 * Exception thrown when a vehicle is already book between two date
 */
public class VehicleAlreadyBookException extends AlreadyBookException{
    /**
     * @param vehicle Vehicle which is already books
     */
    public VehicleAlreadyBookException(Vehicle vehicle) {
        super(vehicle.toString());
    }
}
