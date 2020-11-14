package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.repository.top10.Top10ReserverYearRepository;
import fr.enssat.vehiclesrental.repository.top10.Top10SpenderYearRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Top10ServiceTest {
    @Mock
    private Top10ReserverYearRepository top10ReserverYearRepository;
    @Mock
    private Top10SpenderYearRepository top10SpenderYearRepository;

    @InjectMocks
    private Top10Service top10Service;

    @DisplayName("Get top 10 reserver in year")
    @Test
    public void getTop10ReserverYear() {
        // @TODO Make test
    }

    @DisplayName("Get top 10 spender in year")
    @Test
    public void getTop10SpenderYear() {
        // @TODO Make test
    }
}
