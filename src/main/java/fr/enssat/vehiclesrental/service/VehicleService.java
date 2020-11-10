package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Car;
import fr.enssat.vehiclesrental.model.Motorbike;
import fr.enssat.vehiclesrental.model.Plane;
import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.repository.CarRepository;
import fr.enssat.vehiclesrental.repository.MotorbikeRepository;
import fr.enssat.vehiclesrental.repository.PlaneRepository;
import fr.enssat.vehiclesrental.repository.VehicleRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.VehicleAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static fr.enssat.vehiclesrental.repository.VehicleRepository.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor
@Service
public class VehicleService implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final CarRepository carRepository;
    private final MotorbikeRepository motorbikeRepository;
    private final PlaneRepository planeRepository;

    @Override
    public boolean exists(long id) {
        return vehicleRepository.existsById(id);
    }

    @Override
    public Vehicle getVehicle(long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll(Sort.by(Sort.Direction.ASC, "model"));
    }

    @Override
    public List<Vehicle> searchVehicles(String brand, String model, int nbSeats) {

        //TODO: don't do this! (use accumulation)
        if (!brand.isEmpty() && !model.isEmpty() && nbSeats != 0) {
            return vehicleRepository.findAll(where(hasBrand(brand)).and(hasModel(model).and(hasNbSeats(nbSeats))));
        } else if (!brand.isEmpty() && nbSeats != 0) {
            return vehicleRepository.findAll(where(hasBrand(brand)).and(hasNbSeats(nbSeats)));
        } else {
            return getVehicles();
        }
    }

    //TODO
    @Override
    public List<Car> searchCars(String brand, String model, int nbSeats) {
        return null;
    }

    //TODO
    @Override
    public List<Motorbike> searchMotorbikes(String brand, String model, int nbSeats) {
        return null;
    }

    //TODO
    @Override
    public List<Plane> searchPlanes(String brand, String model, int nbSeats) {
        return null;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicleRepository.existsById(vehicle.getId()))
            throw new VehicleAlreadyExistException(vehicle);
        return vehicleRepository.saveAndFlush(vehicle);
    }

    @Override
    public Vehicle editVehicle(Vehicle vehicle) {
        if (!vehicleRepository.existsById(vehicle.getId()))
            throw new VehicleNotFoundException(String.valueOf(vehicle.getId()));
        return vehicleRepository.saveAndFlush(vehicle);
    }

    @Override
    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
    }
}
