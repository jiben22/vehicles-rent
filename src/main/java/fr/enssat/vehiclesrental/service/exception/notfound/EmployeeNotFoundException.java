package fr.enssat.vehiclesrental.service.exception.notfound;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException(String msg) {
        super("Employee "+msg);
    }
}
