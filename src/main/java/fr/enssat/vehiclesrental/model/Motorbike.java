package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Represent a car hold in the database.
 */
@Entity
@Table(name = "Motorbike")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Motorbike extends Vehicle implements Serializable {
    /**
     * Number of kilometer that a motorbike has been used
     */
    @Column(nullable = false)
    @NonNull
    private int km;

    /**
     * Power of the motorbike
     */
    @Column(nullable = false)
    @NonNull
    private int horsePower;

    /**
     * Constructor of a motorbike
     */
    @Builder
    private Motorbike(long id, String brand, String model, int maximumSpeed, float rentPricePerDay, int nbSeats, String registration, State state, int km, int horsePower, Set<Booking> bookings) {
        super(id, brand, model, maximumSpeed, rentPricePerDay, nbSeats, state,registration, bookings);
        this.km = km;
        this.horsePower = horsePower;
    }
}
