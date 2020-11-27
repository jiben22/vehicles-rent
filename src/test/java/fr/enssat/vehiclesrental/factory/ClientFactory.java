package fr.enssat.vehiclesrental.factory;

import fr.enssat.vehiclesrental.model.Client;

import java.time.LocalDate;

public class ClientFactory {
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

    public static Client getClient2(){
        return Client.builder()
                .id(Long.parseLong("9143686793"))
                .birthdate(LocalDate.parse("2001-08-07"))
                .city("New-York")
                .country("Etats-Unis")
                .email("garth.ennis@hotmail.eu")
                .firstname("Garth")
                .lastname("Ennis")
                .phone("+33425464119")
                .street("5 Rue des CAMELIAS")
                .zipcode("56925")
                .build();
    }
}
