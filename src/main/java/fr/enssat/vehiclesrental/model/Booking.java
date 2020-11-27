package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Booking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate startDate;

    /**
     * Date when a location should end
     */
    @Column(nullable = false, columnDefinition = "DATETIME")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;

    /**
     * Expected number of kilometer define when create booking
     */
    @Column()
    private int expectedNumberKm;

    /**
     * Expected number of hours define when create booking
     */
    @Column()
    private Integer expectedNumberHours;

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
    //@NonNull
    private Status status;

    /**
     * Link a booking to a client
     */
    @ManyToOne
    @JoinColumn(name="id_client")
    private Client client;

    /**
     * Link a booking to a vehicle
     */
    @ManyToOne
    @JoinColumn(name="id_vehicle")
    //@NonNull
    private Vehicle vehicle;

    @Builder
    private Booking(long id, LocalDate startDate, LocalDate endDate, int expectedNumberKm, int expectedNumberHours, float expectedPrice, boolean isDiscount, float discount, Status status, Client client, Vehicle vehicle) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedNumberKm = expectedNumberKm;
        this.expectedNumberHours = expectedNumberHours;
        this.expectedPrice = expectedPrice;
        this.isDiscount = isDiscount;
        this.discount = discount;
        this.status = status;
        this.client = client;
        this.vehicle = vehicle;
    }
}