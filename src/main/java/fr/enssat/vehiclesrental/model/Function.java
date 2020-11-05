package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum Function {

    RESPONSABLE_LOCATION("Responsable location"),
    GESTIONNAIRE_COMMERCIAL("Gestionnaire commercial"),
    GESTIONNAIRE_TECHNIQUE("Gestionnaire technique"),
    GESTIONNAIRE_CLIENT("Gestionnaire client"),
    Collaborateur("Collaborateur");

    public final String label;
}
