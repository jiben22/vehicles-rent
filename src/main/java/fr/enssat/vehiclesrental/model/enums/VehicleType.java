package fr.enssat.vehiclesrental.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VehicleType {

    CAR("Voiture"),
    MOTORBIKE("Moto"),
    PLANE("Avion");

    public final String name;
}
