package fr.enssat.vehiclesrental.service.exception.alreadybook;

/**
 * Mother class used to display exception when something is already books
 */
public class AlreadyBookException extends RuntimeException{
    public AlreadyBookException(String msg) {
        super(msg+" already books !");
    }
}
