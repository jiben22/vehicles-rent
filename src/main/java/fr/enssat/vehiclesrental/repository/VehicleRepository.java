package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Vehicle;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VehicleRepository extends VehicleBaseRepository<Vehicle> {}
