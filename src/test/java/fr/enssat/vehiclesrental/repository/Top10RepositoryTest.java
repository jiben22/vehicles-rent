package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.ClientFactory;
import fr.enssat.vehiclesrental.factory.EmployeeFactory;
import fr.enssat.vehiclesrental.factory.VehicleFactory;
import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Person;
import fr.enssat.vehiclesrental.model.top10.*;
import fr.enssat.vehiclesrental.repository.top10.*;
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
    private Top10SpenderMonthRepository top10SpenderMonthRepository;
    @Autowired
    private Top10SpenderWeekRepository top10SpenderWeekRepository;

    @Autowired
    private Top10ReserverYearRepository top10ReserverYearRepository;
    @Autowired
    private Top10ReserverMonthRepository top10ReserverMonthRepository;
    @Autowired
    private Top10ReserverWeekRepository top10ReserverWeekRepository;

    @DisplayName("Get top 10 of spender in year")
    @Test
    public void findAllSpenderYear() {
        List<Top10SpenderYear> top10SpenderYear = top10SpenderYearRepository.findAll();
        assertEquals(3, top10SpenderYear.size());
    }

    @DisplayName("Get top 10 of spender in year")
    @Test
    public void getFirstSpenderYear() {
        List<Top10SpenderYear> top10SpenderYear = top10SpenderYearRepository.findAll();
        long idExpected = ClientFactory.getClient().getId();
        long idTop10SpenderYear = top10SpenderYear.get(0).getId();
        assertEquals(idExpected, idTop10SpenderYear);
    }

    @DisplayName("Get top 10 of spender in month")
    @Test
    public void findAllSpenderMonth() {
        List<Top10SpenderMonth> top10SpenderMonth = top10SpenderMonthRepository.findAll();
        assertEquals(2, top10SpenderMonth.size());
    }

    @DisplayName("Get top 10 of spender in week")
    @Test
    public void findAllSpenderWeek() {
        List<Top10SpenderWeek> top10SpenderWeek = top10SpenderWeekRepository.findAll();
        assertEquals(1, top10SpenderWeek.size());
    }

    @DisplayName("Get top 10 of reserver in year")
    @Test
    public void findAllReserverYear() {
        List<Top10ReserverYear> top10ReserverYear = top10ReserverYearRepository.findAll();
        assertEquals(3, top10ReserverYear.size());
    }

    @DisplayName("Get top 10 of reserver in month")
    @Test
    public void findAllReserverMonth() {
        List<Top10ReserverMonth> top10ReserverMonth = top10ReserverMonthRepository.findAll();
        assertEquals(2, top10ReserverMonth.size());
    }

    @DisplayName("Get top 10 of reserver in week")
    @Test
    public void findAllReserverWeek() {
        List<Top10ReserverWeek> top10ReserverWeek = top10ReserverWeekRepository.findAll();
        assertEquals(1, top10ReserverWeek.size());
    }
}
