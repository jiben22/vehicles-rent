package fr.enssat.vehiclesrental.service.exception.alreadyrent;

import fr.enssat.vehiclesrental.model.Booking;

/**
 * Thrown when a booking is already rent
 */
public class BookAlreadyRentException extends RuntimeException{
    public BookAlreadyRentException(Booking booking) {
        super(booking.toString()+" is already rent.");
    }
}
