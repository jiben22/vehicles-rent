package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> , JpaSpecificationExecutor<Client> {
    Optional<Client> findByEmail(String email);

    static Specification<Client> hasZipcode(String zipcode) {
        return (client, cq, cb) -> cb.equal(client.get("zipcode"), zipcode);
    }

    static Specification<Client> hasFirstname(String firstname) {
        return (client, cq, cb) -> cb.like(client.get("firstname"), "%" + firstname + "%");
    }

    static Specification<Client> hasLastname(String lastname) {
        return (client, cq, cb) -> cb.like(client.get("lastname"), "%" + lastname + "%");
    }

    static Specification<Client> hasEmail(String email) {
        return (client, cq, cb) -> cb.equal(client.get("email"), email);
    }

    static Specification<Client> isNotArchived() {
        return (client, cq, cb) -> cb.equal(client.get("isArchived"), false);
    }
}
