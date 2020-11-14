package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Plane;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlaneRepository extends VehicleBaseRepository<Plane> {}
