package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    boolean exists(long id);
    Vehicle getVehicle(long id);
    List<Vehicle> getVehicles();
    List<Vehicle> getCars();
    List<Vehicle> getMotorbikes();
    List<Vehicle> getPlanes();
    List<Vehicle> searchVehicles(String brand, String model, int nbSeats);
    List<Vehicle> searchCars(String brand, String model, int nbSeats);
    List<Vehicle> searchMotorbikes(String brand, String model, int nbSeats);
    List<Vehicle> searchPlanes(String brand, String model, int nbSeats);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle editVehicle(Vehicle vehicle);
    void deleteVehicle(long id);
}
