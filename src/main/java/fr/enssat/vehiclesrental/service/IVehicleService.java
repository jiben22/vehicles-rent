package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Car;
import fr.enssat.vehiclesrental.model.Motorbike;
import fr.enssat.vehiclesrental.model.Plane;
import fr.enssat.vehiclesrental.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    boolean exists(long id);
    Vehicle getVehicle(long id);
    List<Vehicle> getVehicles();
    List<Vehicle> searchVehicles(String brand, String model, int nbSeats);
    List<Car> searchCars(String brand, String model, int nbSeats);
    List<Motorbike> searchMotorbikes(String brand, String model, int nbSeats);
    List<Plane> searchPlanes(String brand, String model, int nbSeats);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle editVehicle(Vehicle vehicle);
    void deleteVehicle(long id);
}
