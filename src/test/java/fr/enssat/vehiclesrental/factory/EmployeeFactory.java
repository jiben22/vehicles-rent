package fr.enssat.vehiclesrental.factory;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.Position;

import java.time.LocalDate;

public class EmployeeFactory {

    public static Employee getEmployeeResponsableLocation() {
         return Employee.builder()
            .id(1)
            .lastname("Stark")
            .firstname("Tony")
            .birthdate(LocalDate.parse("1978-05-03"))
            .street("9 rue du chene germain")
            .zipcode("22700")
            .city("Lannion")
            .country("France")
            .email("tony.stark@marvel.com")
            .phone("+33612482274")
            .position(Position.RESPONSABLE_LOCATION)
            .password("Ironman12*")
            .build();
    }

    public static Employee getEmployeeGestionnaireCommercial() {
        return Employee.builder()
                .id(2)
                .lastname("Odinson")
                .firstname("Thor")
                .birthdate(LocalDate.parse("1945-05-03"))
                .street("5 avenue Asgardian")
                .zipcode("22700")
                .city("Lannion")
                .country("France")
                .email("thor@marvel.com")
                .phone("+33612482274")
                .position(Position.GESTIONNAIRE_COMMERCIAL)
                .password("Thor56789*")
                .build();
    }

    public static Employee getEmployeeGestionnaireTechnique() {
        return Employee.builder()
                .id(3)
                .lastname("Jonathan")
                .firstname("Henry")
                .birthdate(LocalDate.parse("1958-05-03"))
                .street("rue")
                .zipcode("0000")
                .city("Nebraska")
                .country("USA")
                .email("antman@marvel.com")
                .phone("+33612482274")
                .position(Position.GESTIONNAIRE_TECHNIQUE)
                .password("Antman123*")
                .build();
    }

    public static Employee getEmployeeCollaborateur() {
        return Employee.builder()
                .id(5)
                .lastname("Rogers")
                .firstname("Steve")
                .birthdate(LocalDate.parse("1985-05-03"))
                .street("0 rue du pole nord")
                .zipcode("0000")
                .city("PoleNord")
                .country("Danemark")
                .email("captain@marvel.com")
                .phone("+33612482274")
                .position(Position.Collaborateur)
                .password("Captain12*")
                .build();
    }
}
