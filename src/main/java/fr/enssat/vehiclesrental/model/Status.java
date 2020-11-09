package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {

    AVAILABLE("Réservation prête"),
    RENTED("En cours de location"),
    ARCHIVED("Réservation archivée");

    public final String name;
}
