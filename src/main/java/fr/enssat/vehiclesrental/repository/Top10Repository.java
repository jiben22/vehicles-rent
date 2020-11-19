package fr.enssat.vehiclesrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface Top10Repository<T,ID extends Serializable> extends JpaRepository<T,ID>{
    List<T> findAll();
}
