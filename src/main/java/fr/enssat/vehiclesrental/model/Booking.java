package fr.enssat.vehiclesrental.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Booking")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
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
    @NonNull
    private Date startDate;

    /**
     * Date when a location should end
     */
    @Column(nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date endDate;

    /**
     * Expected number of kilometer define when create booking
     */
    @Column()
    private int expectedNumberKm;

    /**
     * Expected number of hours define when create booking
     */
    @Column()
    private int expectedNumberHours;

    /**
     * Expected price of booking
     */
    @Column(nullable = false)
    @NonNull
    private float expectedPrice;

    /**
     * Hold if there is a discount on the booking
     */
    @Column(nullable = false)
    @NonNull
    private boolean isDiscount;

    /**
     * It's the size of the discount
     */
    @Column(nullable = false)
    @NonNull
    private float discount;

    /**
     * Represent which state is a booking.
     */
    @Column(nullable = false, length = 45)
    @NonNull
    private Status status;

    /**
     * Link a booking to a client
     */
    @ManyToOne
    @JoinColumn(name="id_client")
    @NonNull
    private Client client;

    /**
     * Link a booking to a vehicle
     */
    @ManyToOne
    @JoinColumn(name="id_vehicle")
    @NonNull
    private Vehicle vehicle;
}
