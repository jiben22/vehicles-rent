package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum State {
    EXCELLENT("Etat excellent"),
    VERY_GOOD("Etat très bien"),
    GOOD("Etat bon"),
    FAIR("Etat passable");

    public String description;
}
