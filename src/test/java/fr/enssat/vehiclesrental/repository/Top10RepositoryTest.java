package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.ClientFactory;
import fr.enssat.vehiclesrental.factory.EmployeeFactory;
import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Person;
import fr.enssat.vehiclesrental.model.top10.Top10ReserverYear;
import fr.enssat.vehiclesrental.model.top10.Top10SpenderYear;
import fr.enssat.vehiclesrental.repository.top10.Top10ReserverYearRepository;
import fr.enssat.vehiclesrental.repository.top10.Top10SpenderYearRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class Top10RepositoryTest {
    @Autowired
    private Top10SpenderYearRepository top10SpenderYearRepository;

    @Autowired
    private Top10ReserverYearRepository top10ReserverYearRepository;

    @DisplayName("Get top 10 of spender in year")
    @Test
    public void findAllSpenderYear() {
        List<Top10SpenderYear> top10SpenderYear = top10SpenderYearRepository.findAll();
        assertEquals(2, top10SpenderYear.size());
    }

    @DisplayName("Get top 10 of reserver in year")
    @Test
    public void findAllReserverYear() {
        List<Top10ReserverYear> top10ReserverYear = top10ReserverYearRepository.findAll();
        assertEquals(2, top10ReserverYear.size());
    }
}
