package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum State {
    EXCELLENT("Etat excellent"),
    VERY_GOOD("Etat très bien"),
    GOOD("Etat bon"),
    FAIR("Etat passable");

    public String description;
}
