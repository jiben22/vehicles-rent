package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface VehicleBaseRepository<T extends Vehicle> extends JpaRepository<T, Long>, JpaSpecificationExecutor<Vehicle> {

    List<T> findByBrand(String brand);
    List<T> findByModel(String model);
    Optional<Vehicle> findByRegistration(String registration);

    static Specification<Vehicle> hasBrand(String brand) {
        return (vehicle, cq, cb) -> cb.like(vehicle.get("brand"), "%" + brand + "%");
    }

    static Specification<Vehicle> hasModel(String model) {
        return (vehicle, cq, cb) -> cb.like(vehicle.get("model"), "%" + model + "%");
    }

    static Specification<Vehicle> hasNbSeats(int nbSeats) {
        return (vehicle, cq, cb) -> cb.equal(vehicle.get("nbSeats"), nbSeats);
    }

    static Specification<Vehicle> isNotArchived() {
        return (vehicle, cq, cb) -> cb.equal(vehicle.get("isArchived"), false);
    }
}