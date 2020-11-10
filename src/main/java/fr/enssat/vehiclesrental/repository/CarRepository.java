package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    static Specification<Car> hasBrand(String brand) {
        return (car, cq, cb) -> cb.like(car.get("brand"), "%" + brand + "%");
    }

    static Specification<Car> hasModel(String model) {
        return (car, cq, cb) -> cb.like(car.get("model"), "%" + model + "%");
    }

    static Specification<Car> hasNbSeats(int nbSeats) {
        return (car, cq, cb) -> cb.equal(car.get("nbSeats"), nbSeats);
    }
}
