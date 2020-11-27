package fr.enssat.vehiclesrental.service.exception.alreadyexists;

import fr.enssat.vehiclesrental.model.Booking;

/**
 * Exception to thrown when a booking already exist
 */
public class BookingAlreadyExistsException extends AlreadyExistsException{
    /**
     * @param booking Booking which already exists
     */
    public BookingAlreadyExistsException(Booking booking) {
        super(booking.toString());
    }
}
