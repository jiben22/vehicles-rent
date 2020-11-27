package fr.enssat.vehiclesrental.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    MOST_SPENDER_CLIENT("Clients les plus dépensiers"),
    MOST_RESERVER_CLIENT("Clients qui réservent le plus");

    public final String name;
}
