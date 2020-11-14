package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.top10.*;
import fr.enssat.vehiclesrental.repository.top10.*;

import java.util.List;

public class Top10Service implements ITop10Service{
    /**
     * Initialise all repositories allowing to get different top 10
     */
    Top10SpenderYearRepository top10SpenderYearRepository;
    Top10SpenderMonthRepository top10SpenderMonthRepository;
    Top10SpenderWeekRepository top10SpenderWeekRepository;

    Top10ReserverYearRepository top10ReserverYearRepository;
    Top10ReserverMonthRepository top10ReserverMonthRepository;
    Top10ReserverWeekRepository top10ReserverWeekRepository;

    /**
     * Allow to get top 10 of spender in the current year
     *
     * @return List of spender in the current year
     */
    @Override
    public List<Top10SpenderYear> getTop10SpenderYear() {
        return top10SpenderYearRepository.findAll();
    }

    /**
     * Allow to get top 10 of spender in the current month
     *
     * @return List of spender in the current month
     */
    @Override
    public List<Top10SpenderMonth> getTop10SpenderMonth() {
        return top10SpenderMonthRepository.findAll();
    }

    /**
     * Allow to get top 10 of spender in the current week
     *
     * @return List of spender in the current week
     */
    @Override
    public List<Top10SpenderWeek> getTop10SpenderWeek() {
        return top10SpenderWeekRepository.findAll();
    }

    /**
     * Allow to get top 10 of reserver in the current year
     *
     * @return ist of reserver in the current year
     */
    @Override
    public List<Top10ReserverYear> getTop10ReserverYear() {
        return top10ReserverYearRepository.findAll();
    }

    /**
     * Allow to get top 10 of reserver in the current month
     *
     * @return ist of reserver in the current month
     */
    @Override
    public List<Top10ReserverMonth> getTop10ReserverMonth() {
        return top10ReserverMonthRepository.findAll();
    }

    /**
     * Allow to get top 10 of reserver in the current week
     *
     * @return ist of reserver in the current week
     */
    @Override
    public List<Top10ReserverWeek> getTop10ReserverWeek() {
        return top10ReserverWeekRepository.findAll();
    }

}
