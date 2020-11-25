package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.controller.constants.Constants;
import fr.enssat.vehiclesrental.controller.constants.Constants.ChartController.GetCharts;
import fr.enssat.vehiclesrental.model.Client;
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

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static fr.enssat.vehiclesrental.controller.constants.Constants.ChartController.GetCharts.*;
import static fr.enssat.vehiclesrental.controller.constants.Constants.ClientController.CLIENTS;
import static fr.enssat.vehiclesrental.controller.constants.Constants.Controller.TITLE;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChartController {

    private final Top10Service top10Service;

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_COMMERCIAL.label)")
    @GetMapping(URL)
    public String showStatistics(Model springModel, @PathVariable Type type, @PathVariable Interval interval) {
        log.info(String.format("GET %s", GetCharts.URL));
        springModel.addAttribute(TITLE, GetCharts.TITLE);

        System.out.println(type);
        System.out.println(interval);

//        List<Client> clients;
//        switch (type){
//            case MOST_RESERVER_CLIENT:
//                clients = getTop10Reserver(interval);
//                break;
//            case MOST_SPENDER_CLIENT:
//                clients = getTop10Spender(interval);
//                break;
//            default:
//                log.error(String.valueOf(new IllegalStateException("Unexpected value: " + type)));
//                clients = Collections.emptyList();
//                break;
//        }
//        List<? extends Person> clients = Collections.singletonList(getClient());
        Map<String, Integer> clients = new LinkedHashMap<>();
        clients.put("Tim", 100);
        clients.put("Bertrand", 90);
        clients.put("Denis", 80);
        clients.put("Bernard", 70);
        clients.put("Jacques", 60);
        clients.put("Mathilde", 50);
        clients.put("Carole", 40);
        clients.put("Sophie", 30);
        clients.put("Julie", 20);
        clients.put("Toufik", 10);

        springModel.addAttribute(CLIENTS, clients);
        System.out.println(clients);

        return GetCharts.VIEW;
    }

    private List<Client> getTop10Depensiers(Interval interval){
        switch (interval){
            case ONE_WEEK:
                //SELECT client.* FROM locateam.booking inner join locateam.client on booking.id_client = client.id where week(start_date) = week(now()) group by client.id order by sum(booking.expected_price) desc limit 10
                break;
            case ONE_MONTH:
                //SELECT client.* FROM locateam.booking inner join locateam.client on booking.id_client = client.id where month(start_date) = month(now()) group by client.id order by sum(booking.expected_price) desc limit 10
                break;
            case ONE_YEAR:
                //SELECT client.* FROM locateam.booking inner join locateam.client on booking.id_client = client.id where year(start_date) = year(now()) group by client.id order by sum(booking.expected_price) desc limit 10
                break;
            default:
                // @TODO Déclencher une exception ("This type of interval doesn't exist")
                break;
        }
        return null;
    }

    private List<Client> getTop10Reservations(Interval interval){
        switch (interval){
            case ONE_WEEK:
                //SELECT client.* FROM locateam.booking inner join locateam.client on booking.id_client = client.id where week(start_date) = week(now()) group by client.id order by count(*) desc limit 10
                break;
            case ONE_MONTH:
                //SELECT client.* FROM locateam.booking inner join locateam.client on booking.id_client = client.id where month(start_date) = month(now()) group by client.id order by count(*) desc limit 10
                break;
            case ONE_YEAR:
                //SELECT client.* FROM locateam.booking inner join locateam.client on booking.id_client = client.id where year(start_date) = year(now()) group by client.id order by count(*) desc limit 10
                break;
            default:
                // @TODO Déclencher une exception ("This type of interval doesn't exist")
                break;
        }
        return null;
    }

    public static Client getClient(){
        return Client.builder()
                .id(Long.parseLong("9143686792"))
                .birthdate(LocalDate.parse("1998-12-06"))
                .city("Lannion")
                .country("France")
                .email("tim.bradstreet@gmail.com")
                .firstname("Tim")
                .lastname("Bradstreet")
                .phone("+33938333613")
                .street("6 Rue des ARTILLEURS")
                .zipcode("35288")
                .build();
    }
}
