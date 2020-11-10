package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Plane;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long>, JpaSpecificationExecutor<Plane> {

    static Specification<Plane> hasBrand(String brand) {
        return (plane, cq, cb) -> cb.like(plane.get("brand"), "%" + brand + "%");
    }

    static Specification<Plane> hasModel(String model) {
        return (plane, cq, cb) -> cb.like(plane.get("model"), "%" + model + "%");
    }

    static Specification<Plane> hasNbSeats(int nbSeats) {
        return (plane, cq, cb) -> cb.equal(plane.get("nbSeats"), nbSeats);
    }
}
