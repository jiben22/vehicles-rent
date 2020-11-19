package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikeRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {}
