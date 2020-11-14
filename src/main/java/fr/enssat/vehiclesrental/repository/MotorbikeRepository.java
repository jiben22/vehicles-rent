package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Motorbike;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MotorbikeRepository extends VehicleBaseRepository<Motorbike> {}
