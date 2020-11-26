package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.factory.ClientFactory;
import fr.enssat.vehiclesrental.model.top10.*;
import fr.enssat.vehiclesrental.repository.top10.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class Top10ServiceTest {

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

    @Mock
    private Top10Service top10Service;

    @DisplayName("Get top 10 reserver in year")
    @Test
    public void getTop10ReserverYear() {
        when(top10Service.getTop10ReserverYear()).thenReturn(top10ReserverYearRepository.findAll());
        List<Top10ReserverYear> top10ReserverYears = top10Service.getTop10ReserverYear();
        assertEquals(3, top10ReserverYears.size());
    }

    @DisplayName("Get top 10 reserver in month")
    @Test
    public void getTop10ReserverMonth() {
        when(top10Service.getTop10ReserverMonth()).thenReturn(top10ReserverMonthRepository.findAll());
        List<Top10ReserverMonth> top10ReserverMonth = top10Service.getTop10ReserverMonth();
        assertEquals(2, top10ReserverMonth.size());
    }

    @DisplayName("Get top 10 reserver in week")
    @Test
    public void getTop10ReserverWeek() {
        when(top10Service.getTop10ReserverWeek()).thenReturn(top10ReserverWeekRepository.findAll());
        List<Top10ReserverWeek> top10ReserverWeek = top10Service.getTop10ReserverWeek();
        assertEquals(1, top10ReserverWeek.size());
    }

    @DisplayName("Get top 10 spender in year")
    @Test
    public void getTop10SpenderYear() {
        when(top10Service.getTop10SpenderYear()).thenReturn(top10SpenderYearRepository.findAll());
        List<Top10SpenderYear> top10SpenderYear = top10Service.getTop10SpenderYear();
        assertEquals(3, top10SpenderYear.size());
        assertEquals(ClientFactory.getClient().getId(), top10SpenderYear.get(0).getId());
    }

    @DisplayName("Get top 10 spender in month")
    @Test
    public void getTop10SpenderMonth() {
        when(top10Service.getTop10SpenderMonth()).thenReturn(top10SpenderMonthRepository.findAll());
        List<Top10SpenderMonth> top10SpenderMonth = top10Service.getTop10SpenderMonth();
        assertEquals(2, top10SpenderMonth.size());
    }

    @DisplayName("Get top 10 spender in week")
    @Test
    public void getTop10SpenderWeek() {
        when(top10Service.getTop10SpenderWeek()).thenReturn(top10SpenderWeekRepository.findAll());
        List<Top10SpenderWeek> top10SpenderWeek = top10Service.getTop10SpenderWeek();
        assertEquals(1, top10SpenderWeek.size());
    }
}
