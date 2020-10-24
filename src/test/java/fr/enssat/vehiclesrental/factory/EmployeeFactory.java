package fr.enssat.vehiclesrental.factory;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.Position;

import java.time.LocalDate;
import java.util.Optional;

public class EmployeeFactory {

    public static Employee getEmployeeResponsableLocation() {
         return Employee.builder()
            .id("157314099170601")
            .lastname("Stark")
            .firstname("Tony")
            .birthdate(LocalDate.parse("1978-05-03"))
            .street("9 rue du chene germain")
            .zipcode("22700")
            .city("Lannion")
            .country("France")
            .email("tony.stark@marvel.com")
            .position(Position.RESPONSABLE_LOCATION)
            .password("Ironman12*")
            .build();
    }

    public static Optional<Employee> getEmployeeGestionnaireCommercial() {
        return Optional.ofNullable(Employee.builder()
                .id("157314099170602")
                .lastname("Odinson")
                .firstname("Thor")
                .birthdate(LocalDate.parse("1945-05-03"))
                .street("5 avenue Asgardian")
                .zipcode("22700")
                .city("Lannion")
                .country("France")
                .email("thor@marvel.com")
                .position(Position.GESTIONNAIRE_COMMERCIAL)
                .password("Thor56789*")
                .build());
    }

    public static Employee getEmployeeGestionnaireTechnique() {
        return Employee.builder()
                .id("157314099170603")
                .lastname("Jonathan")
                .firstname("Henry")
                .birthdate(LocalDate.parse("1958-05-03"))
                .street("rue")
                .zipcode("0000")
                .city("Nebraska")
                .country("USA")
                .email("antman@marvel.com")
                .position(Position.GESTIONNAIRE_TECHNIQUE)
                .password("Antman123*")
                .build();
    }

    public static Employee getEmployeeCollaborateur() {
        return Employee.builder()
                .id("157314099170605")
                .lastname("Rogers")
                .firstname("Steve")
                .birthdate(LocalDate.parse("1985-05-03"))
                .street("0 rue du pole nord")
                .zipcode("0000")
                .city("PoleNord")
                .country("Danemark")
                .email("captain@marvel.com")
                .position(Position.Collaborateur)
                .password("Captain12*")
                .build();
    }
}