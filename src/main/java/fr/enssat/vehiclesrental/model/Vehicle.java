package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Represent a Vehicle used to create other vehicle
 * There is no table Vehicle in database
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Vehicle implements Serializable {

    /**
     * Id of a vehicle used to find him in the database.
     *
     * We use sequence strategie to generate id, because it creates unique id
     * for all chilren even between different entity.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    /**
     * Brand of a vehicle
     */
    @Column(nullable = false, length = 128)
    @Size(min = 1, max = 128, message = "La marque ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    @NonNull
    private String brand;
    /**
     * Model of a vehicle
     */
    @Column(nullable = false, length = 128)
    @Size(min = 1, max = 128, message = "La modèle ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    @NonNull
    private String model;
    /**
     * Maximum speed of a vehicle
     */
    @Column(nullable = false)
    @NonNull
    private int maximumSpeed;
    /**
     * Price of a rent per day
     */
    @Column(nullable = false)
    @NonNull
    private float rentPricePerDay;
    /**
     * Number of seat available in the vehicle
     */
    @Column(nullable = false)
    @NonNull
    private int nbSeats;
    /**
     * General state of a vehicle
     */
    @Column(nullable = false)
    @NonNull
    private State state;
    /**
     * Reegistration of a vehicle
     */
    @Column(nullable = false)
    @NonNull
    private String registration;

    /**
     * Represent list of bookings link to the current vehicle.
     */
    @OneToMany(mappedBy="vehicle")
    private Set<Booking> bookings;
}
