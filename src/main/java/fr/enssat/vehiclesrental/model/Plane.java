package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Represent a plane hold in the database.
 */
@Entity
@Table(name = "Plane")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Plane extends Vehicle implements Serializable {
    /**
     * Number of hours when the plane has been used
     */
    @Column(nullable = false)
    @NonNull
    private int nbHours;

    /**
     * Number of engines used by the plane
     */
    @Column(nullable = false)
    @NonNull
    private int nbEngines;

    /**
     * Constructor of an a plane
     */
    @Builder
    private Plane(long id, String brand,String model, int maximumSpeed, float rentPricePerDay, int nbSeats, State state, String registration, Set<Booking> bookings, int nbHours, int nbEngines) {
        super(id, brand, model, maximumSpeed, rentPricePerDay, nbSeats, state,registration, bookings);
        this.nbHours = nbHours;
        this.nbEngines = nbEngines;
    }
}