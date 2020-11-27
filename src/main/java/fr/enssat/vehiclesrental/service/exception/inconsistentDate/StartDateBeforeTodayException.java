package fr.enssat.vehiclesrental.service.exception.inconsistentDate;

public class StartDateBeforeTodayException extends InconsistenDateException{
    public StartDateBeforeTodayException() {
        super("Start date is before today date");
    }
}
