package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    boolean exists(long id);
    Vehicle getVehicle(long id);
    Vehicle getVehicleByRegistration(String registration);
    List<Vehicle> getVehicles();
    List<Vehicle> getCars();
    List<Vehicle> getMotorbikes();
    List<Vehicle> getPlanes();
    List<Vehicle> searchVehicles(String brand, String model, int nbSeats);
    List<? extends Vehicle> searchCars(String brand, String model, int nbSeats);
    List<? extends Vehicle> searchMotorbikes(String brand, String model, int nbSeats);
    List<? extends Vehicle> searchPlanes(String brand, String model, int nbSeats);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle editVehicle(Vehicle vehicle);
    Vehicle archiveVehicle(long id);
}
