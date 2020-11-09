package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Represent a Vehicule used to create other vehicule
 * There is no table Vehicule in database
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Vehicule implements Serializable {

    /**
     * Id of a vehicule used to find him in the database.
     *
     * We use sequence strategie to generate id, because it creates unique id
     * for all chilren even between different entity.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    /**
     * Brand of a vehicule
     */
    @Column(nullable = false, length = 128)
    @Size(min = 1, max = 128, message = "La marque ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    @NonNull
    private String brand;
    /**
     * Model of a vehicule
     */
    @Column(nullable = false, length = 128)
    @Size(min = 1, max = 128, message = "La modèle ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    @NonNull
    private String model;
    /**
     * Maximum speed of a vehicule
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
     * Number of seat available in the vehicule
     */
    @Column(nullable = false)
    @NonNull
    private int nbSeats;
    /**
     * General state of a vehicule
     */
    @Column(nullable = false)
    @NonNull
    private State state;

    /**
     * Represent list of bookings link to the current vehicule.
     */
    @OneToMany(mappedBy="vehicule")
    private Set<Booking> bookings;
}
