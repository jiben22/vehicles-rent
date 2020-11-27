package fr.enssat.vehiclesrental.service.exception.alreadyarchived;

import fr.enssat.vehiclesrental.model.Booking;

/**
 * Thrown when a book is already archived
 */
public class BookAlreadyArchivedException extends RuntimeException {
    public BookAlreadyArchivedException(Booking booking) {
        super(booking.toString()+" is already rent.");
    }
}
