package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Interval {
    ONE_WEEK("Une semaine"),
    ONE_MONTH("Un mois"),
    ONE_YEAR("Un an");

    public final String name;
}
