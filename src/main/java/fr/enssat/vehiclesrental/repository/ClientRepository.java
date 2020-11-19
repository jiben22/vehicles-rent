package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> , JpaSpecificationExecutor<Client> {

    static Specification<Client> hasZipcode(String zipcode) {
        return (client, cq, cb) -> cb.equal(client.get("zipcode"), zipcode);
    }




}
