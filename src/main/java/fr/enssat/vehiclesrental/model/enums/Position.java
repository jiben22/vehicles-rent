package fr.enssat.vehiclesrental.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Position {

    RESPONSABLE_LOCATION("Responsable location"),
    GESTIONNAIRE_COMMERCIAL("Gestionnaire commercial"),
    GESTIONNAIRE_TECHNIQUE("Gestionnaire technique"),
    GESTIONNAIRE_CLIENT("Gestionnaire client"),
    COLLABORATEUR("Collaborateur");

    public final String label;
}
