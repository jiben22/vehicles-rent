package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

    List<Vehicle> findByBrand(String brand);
    List<Vehicle> findByModel(String model);

    static Specification<Vehicle> hasBrand(String brand) {
        return (vehicle, cq, cb) -> cb.like(vehicle.get("brand"), "%" + brand + "%");
    }

    static Specification<Vehicle> hasModel(String model) {
        return (vehicle, cq, cb) -> cb.like(vehicle.get("model"), "%" + model + "%");
    }

    static Specification<Vehicle> hasNbSeats(int nbSeats) {
        return (vehicle, cq, cb) -> cb.equal(vehicle.get("nbSeats"), nbSeats);
    }
}
