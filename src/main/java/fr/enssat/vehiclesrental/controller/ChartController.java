package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.Person;
import fr.enssat.vehiclesrental.model.enums.Interval;
import fr.enssat.vehiclesrental.model.enums.Type;
import fr.enssat.vehiclesrental.service.Top10Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.ClientController.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.ChartController.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.Controller.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChartController {

    private final Top10Service top10Service;

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_COMMERCIAL.label)")
    @GetMapping(GetCharts.URL)
    public String showStatistics(Model springModel, @RequestParam Type type, @RequestParam Interval interval) {
        log.info(String.format("GET %s", GetCharts.URL));
        springModel.addAttribute(TITLE, GetCharts.TITLE);
        springModel.addAttribute(GetCharts.TOP10, String.format(GetCharts.TOP10_NAME, type.name));
        springModel.addAttribute(GetCharts.INTERVAL, String.format(GetCharts.INTERVAL_NAME, interval.name));

        Map<String, Integer> clients;
        switch (type){
            case MOST_RESERVER_CLIENT:
                clients = getTop10Reserver(interval);
                break;
            case MOST_SPENDER_CLIENT:
                clients = getTop10Spender(interval);
                break;
            default:
                log.error(String.valueOf(new IllegalStateException("Unexpected value: " + type)));
                clients = new LinkedHashMap<>();
                break;
        }

        springModel.addAttribute(CLIENTS, clients);

        return GetCharts.VIEW;
    }

    private Map<String, Integer> getTop10Spender(Interval interval) {
        log.info(String.format("Get TOP 10 spender %s", interval.name));

        List<? extends Person> top10Spender = Collections.emptyList();
        switch (interval) {
            case ONE_WEEK:
                top10Spender = top10Service.getTop10SpenderWeek();
                break;
            case ONE_MONTH:
                top10Spender = top10Service.getTop10SpenderMonth();
                break;
            case ONE_YEAR:
                top10Spender = top10Service.getTop10SpenderYear();
                break;
            default:
                log.error(String.valueOf(new IllegalStateException("Unexpected value: " + interval)));
                break;
        }

        return createCharts(top10Spender);
    }

    private Map<String, Integer> getTop10Reserver(Interval interval) {
        log.info(String.format("Get TOP 10 spender %s", interval.name));

        List<? extends Person> top10Reserver = Collections.emptyList();;
        switch (interval) {
            case ONE_WEEK:
                top10Reserver = top10Service.getTop10ReserverWeek();
                break;
            case ONE_MONTH:
                top10Reserver = top10Service.getTop10ReserverMonth();
                break;
            case ONE_YEAR:
                top10Reserver = top10Service.getTop10ReserverYear();
                break;
            default:
                log.error(String.valueOf(new IllegalStateException("Unexpected value: " + interval)));
                break;
        }

        return createCharts(top10Reserver);
    }

    private Map<String, Integer> createCharts(List<? extends Person> top10) {
        Map<String, Integer> clients = new LinkedHashMap<>();
        List<Integer> points = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        for (int i = 0; i < top10.size(); i++) {
            Person person = top10.get(i);
            clients.put(String.format("%s %s", person.getLastname(), person.getFirstname()), points.get(i));
        }

        return clients;
    }
}
