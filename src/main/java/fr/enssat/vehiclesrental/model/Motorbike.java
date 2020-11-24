package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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
    @Min(value = 0, message="Le nombre de kilomètres doit être positif !")
    @NonNull
    private int km;

    /**
     * Power of the motorbike
     */
    @Column(nullable = false)
    @Min(value = 0, message="La puissance din doit être positif !")
    @NonNull
    private int horsePower;

    /**
     * Constructor of a motorbike
     */
    @Builder
    private Motorbike(long id, String brand, String model, int maximumSpeed, float rentPricePerDay, int nbSeats, String registration, State state, int km, int horsePower, boolean isArchived, Set<Booking> bookings) {
        super(id, brand, model, maximumSpeed, rentPricePerDay, nbSeats, state,registration, isArchived, bookings);
        this.km = km;
        this.horsePower = horsePower;
    }
}