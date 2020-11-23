package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Min(value = 0, message="La vitesse maximum doit être positive !")
    @NonNull
    private int maximumSpeed;
    /**
     * Price of a rent per day
     */
    @Column(nullable = false)
    @Min(value = 0, message="Le prix de location par jour doit être positif !")
    @NonNull
    private float rentPricePerDay;
    /**
     * Number of seat available in the vehicle
     */
    @Column(nullable = false)
    @Min(value = 0, message="Le nombre de sièges doit être positif !")
    @NonNull
    private int nbSeats;
    /**
     * General state of a vehicle
     */
    @Column(nullable = false)
    @NonNull
    private State state;
    /**
     * Hold the registration of a vehicle
     */
    @Column(nullable = false, unique = true, length = 9)
    @Size(min = 1, max = 9, message = "L'immatriculation ne peut pas être vide et ne doit pas dépasser les 9 caractères !")
    private String registration;

    /**
     * Represent list of bookings link to the current vehicle.
     */
    @OneToMany(mappedBy="vehicle")
    private Set<Booking> bookings;
}
