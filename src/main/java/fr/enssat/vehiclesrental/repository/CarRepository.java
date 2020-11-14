package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Car;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CarRepository extends VehicleBaseRepository<Car> {}
