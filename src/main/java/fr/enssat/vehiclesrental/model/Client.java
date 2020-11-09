package fr.enssat.vehiclesrental.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represent a customer hold in the database.
 */
@Entity
@Table(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Person implements Serializable {
    /**
     * Constructor of a Client
     */
    @Builder
    private Client(long id, String lastname, String firstname, LocalDate birthdate, String street, String zipcode, String city, String country, String email, String phone) {
        super(id, lastname, firstname, birthdate, street, zipcode, city, country, email,phone);
    }

    /**
     * Represent list of bookings link to the current client.
     */
    @OneToMany(mappedBy="client")
    private Set<Booking> bookings;
}