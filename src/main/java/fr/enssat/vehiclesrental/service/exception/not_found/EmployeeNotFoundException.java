package fr.enssat.vehiclesrental.service.exception.not_found;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException(String msg) {
        super("Employee "+msg);
    }
}
