package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.Interval;
import fr.enssat.vehiclesrental.model.Type;

import java.util.List;

public class ChartController {
    public List<Client> calculChart(Type typ, Interval interval){
        switch (typ){
            case MOST_RESERVER_CLIENT:
                // @TODO Récuperer les client qui réservent le plus
                getTop10Reservations(interval);
                break;
            case MOST_SPENDER_CLIENT:
                // @TODO Récuperer les client qui dépensent le plus
                getTop10Depensiers(interval);
            default:
                // @TODO Déclencher une exception ("This type of stats doesn't exist")
                break;
        }
        return null;
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
}
