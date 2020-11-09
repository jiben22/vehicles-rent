package fr.enssat.vehiclesrental.service.exception.alreadyexists;

import fr.enssat.vehiclesrental.model.Employee;

public class EmployeeAlreadyExistException extends AlreadyExistsException {
    public EmployeeAlreadyExistException(Employee employee) {
        super(employee.toString());
    }
}
