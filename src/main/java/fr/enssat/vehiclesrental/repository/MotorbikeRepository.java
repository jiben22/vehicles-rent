package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Motorbike;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Long>, JpaSpecificationExecutor<Motorbike> {

    static Specification<Motorbike> hasBrand(String brand) {
        return (motorbike, cq, cb) -> cb.like(motorbike.get("brand"), "%" + brand + "%");
    }

    static Specification<Motorbike> hasModel(String model) {
        return (motorbike, cq, cb) -> cb.like(motorbike.get("model"), "%" + model + "%");
    }

    static Specification<Motorbike> hasNbSeats(int nbSeats) {
        return (motorbike, cq, cb) -> cb.equal(motorbike.get("nbSeats"), nbSeats);
    }
}
