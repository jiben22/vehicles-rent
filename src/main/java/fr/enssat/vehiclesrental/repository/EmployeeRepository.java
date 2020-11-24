package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.enums.Position;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    List<Employee> findByFirstname(String firstname);
    List<Employee> findByLastname(String lastname);
    Optional<Employee> findByEmail(String email);

    static Specification<Employee> hasPosition(Position position) {
        return (employee, cq, cb) -> cb.equal(employee.get("position"), position);
    }

    static Specification<Employee> hasFirstname(String firstname) {
        return (employee, cq, cb) -> cb.like(employee.get("firstname"), "%" + firstname + "%");
    }

    static Specification<Employee> hasLastname(String lastname) {
        return (employee, cq, cb) -> cb.like(employee.get("lastname"), "%" + lastname + "%");
    }

    static Specification<Employee> hasEmail(String email) {
        return (employee, cq, cb) -> cb.equal(employee.get("email"), email);
    }

    static Specification<Employee> hasZipcode(String zipcode) {
        return (employee, cq, cb) -> cb.equal(employee.get("zipcode"), zipcode);
    }
}
