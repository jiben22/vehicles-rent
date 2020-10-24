package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Position {

    RESPONSABLE_LOCATION("Responsable location"),
    GESTIONNAIRE_COMMERCIAL("Gestionnaire commercial"),
    GESTIONNAIRE_TECHNIQUE("Gestionnaire technique"),
    GESTIONNAIRE_CLIENT("Gestionnaire client"),
    Collaborateur("Collaborateur");

    public final String label;
}
