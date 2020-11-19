package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> , JpaSpecificationExecutor<Client> {

    static Specification<Client> hasZipcode(String zipcode) {
        return (client, cq, cb) -> cb.equal(client.get("zipcode"), zipcode);
    }




}
