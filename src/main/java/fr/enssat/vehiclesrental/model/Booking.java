package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Booking")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@PrimaryKeyJoinColumn(name = "id")
public class Booking implements Serializable {
    /**
     * Id of a booking used to find her in database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Date when a location should start
     */
    @Column(nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    /**
     * Date when a location should end
     */
    @Column(nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    /**
     * Expected number of kilometer define when create booking
     */
    @Column(nullable = false)
    private int expectedNumberKm;

    /**
     * Expected price of booking
     */
    @Column(nullable = false)
    private float expectedPrice;

    /**
     * Hold if there is a discount on the booking
     */
    @Column(nullable = false)
    private boolean isDiscount;

    /**
     * It's the size of the discount
     */
    @Column(nullable = false)
    private float discount;

    /**
     * Represent which state is a booking.
     */
    @Column(nullable = false, length = 45)
    private Status status;

    /**
     * Link a booking to a client
     */
    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;

    /**
     * Link a booking to a vehicule
     */
    @ManyToOne
    @JoinColumn(name="id_vehicule")
    private Vehicle vehicle;
}