package fr.enssat.vehiclesrental.service.exception.notfound;

public class BookingNotFoundException extends NotFoundException{

    public BookingNotFoundException(String msg) {
        super(" Client "+msg);
    }
}
