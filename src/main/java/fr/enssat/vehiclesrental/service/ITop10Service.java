package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.top10.*;

import java.util.List;

public interface ITop10Service {
    List<Top10SpenderYear> getTop10SpenderYear();
    List<Top10SpenderMonth> getTop10SpenderMonth();
    List<Top10SpenderWeek> getTop10SpenderWeek();

    List<Top10ReserverYear> getTop10ReserverYear();
    List<Top10ReserverMonth> getTop10ReserverMonth();
    List<Top10ReserverWeek> getTop10ReserverWeek();
}
