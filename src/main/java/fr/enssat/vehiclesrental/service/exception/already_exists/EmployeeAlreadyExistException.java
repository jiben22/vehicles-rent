package fr.enssat.vehiclesrental.service.exception.already_exists;

import fr.enssat.vehiclesrental.model.Employee;

public class EmployeeAlreadyExistException extends AlreadyExistsException {
    public EmployeeAlreadyExistException(Employee employee) {
        super(employee.toString());
    }
}
