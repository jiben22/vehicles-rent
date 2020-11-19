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
@Table(name = "Car")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends Vehicle implements Serializable {
    /**
     * Number of kilometer that a car has been used
     */
    @Column(nullable = false)
    @NonNull
    private int km;

    /**
     * Power of the car
     */
    @Column(nullable = false)
    @NonNull
    private int horsePower;

    @Builder
    public Car(long id, String brand, String model, int maximumSpeed, float rentPricePerDay, int nbSeats, State state, String registration, Set<Booking> bookings, int km, int horsePower) {
        super(id, brand, model, maximumSpeed, rentPricePerDay, nbSeats, state, registration,bookings);
        this.km = km;
        this.horsePower = horsePower;
    }
}
