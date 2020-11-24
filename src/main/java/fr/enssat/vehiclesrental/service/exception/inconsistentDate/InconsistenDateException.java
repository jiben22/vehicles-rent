package fr.enssat.vehiclesrental.service.exception.inconsistentDate;

public class InconsistenDateException extends RuntimeException{
    public InconsistenDateException(String msg) {
        super("Inconsistent date :"+msg);
    }

}
