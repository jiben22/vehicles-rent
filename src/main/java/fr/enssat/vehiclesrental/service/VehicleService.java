package fr.enssat.vehiclesrental.service;

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
import java.util.Objects;

import static fr.enssat.vehiclesrental.repository.VehicleBaseRepository.*;
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
    public Vehicle getVehicleByRegistration(String registration) {
        return vehicleRepository.findByRegistration(registration).orElseThrow(() -> new VehicleNotFoundException(String.valueOf(registration)));
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll(where(isNotArchived()), Sort.by(Sort.Direction.ASC, "model"));
    }

    @Override
    public List<Vehicle> getCars() {
        return carRepository.findAll(where(isNotArchived()), Sort.by(Sort.Direction.ASC, "model"));
    }

    @Override
    public List<Vehicle> getMotorbikes() {
        return motorbikeRepository.findAll(where(isNotArchived()), Sort.by(Sort.Direction.ASC, "model"));
    }

    @Override
    public List<Vehicle> getPlanes() {
        return planeRepository.findAll(where(isNotArchived()), Sort.by(Sort.Direction.ASC, "model"));
    }

    @Override
    public List<Vehicle> searchVehicles(String brand, String model, int nbSeats) {
        Specification<Vehicle> vehicleSpecification = buildSpecification(brand, model, nbSeats);
        if (vehicleSpecification != null) {
            return vehicleRepository.findAll(vehicleSpecification);
        } else {
            return getVehicles();
        }
    }

    @Override
    public List<? extends Vehicle> searchCars(String brand, String model, int nbSeats) {
        Specification<Vehicle> vehicleSpecification = buildSpecification(brand, model, nbSeats);
        if (vehicleSpecification != null) {
            return carRepository.findAll(vehicleSpecification);
        } else {
            return getCars();
        }
    }

    @Override
    public List<? extends Vehicle> searchMotorbikes(String brand, String model, int nbSeats) {
        Specification<Vehicle> vehicleSpecification = buildSpecification(brand, model, nbSeats);
        if (vehicleSpecification != null) {
            return motorbikeRepository.findAll(vehicleSpecification);
        } else {
            return getMotorbikes();
        }
    }

    @Override
    public List<? extends Vehicle> searchPlanes(String brand, String model, int nbSeats) {
        Specification<Vehicle> vehicleSpecification = buildSpecification(brand, model, nbSeats);
        if (vehicleSpecification != null) {
            return planeRepository.findAll(vehicleSpecification);
        } else {
            return getPlanes();
        }
    }

    private Specification<Vehicle> buildSpecification(String brand, String model, int nbSeats) {
        List<Specification<Vehicle>> specifications = new ArrayList<>();
        specifications.add(isNotArchived());
        if (!brand.isEmpty()) specifications.add(hasBrand(brand));
        if (!model.isEmpty()) specifications.add(hasModel(model));
        if (nbSeats > 0) specifications.add(hasNbSeats(nbSeats));

        if (specifications.size() > 0) {
            Specification<Vehicle> vehicleSpecification = where(specifications.get(0));
            specifications.remove(0);
            for (Specification<Vehicle> specification: specifications) {
                vehicleSpecification = Objects.requireNonNull(vehicleSpecification).and(specification);
            }

            return vehicleSpecification;
        } else {
            return null;
        }
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
    public Vehicle archiveVehicle(long id) {
        Vehicle vehicle = getVehicle(id);
        vehicle.setArchived(true);
        return vehicleRepository.saveAndFlush(vehicle);
    }
}
