package fr.enssat.vehiclesrental.service.exception.inconsistentDate;

public class StartDateAfterEndDateExceptionException extends InconsistenDateException {
    public StartDateAfterEndDateExceptionException() {
        super("Start date is after end date");
    }
}
