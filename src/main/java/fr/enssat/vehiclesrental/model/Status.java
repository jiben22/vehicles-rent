package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    AVAILABLE("Réservation prête"),
    RENTED("En cours de location"),
    ARCHIVED("Réservation archivée");

    public final String name;
}
