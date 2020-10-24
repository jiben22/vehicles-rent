package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.repository.EmployeeRepository;
import fr.enssat.vehiclesrental.service.exception.already_exists.EmployeeAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.not_found.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public Employee getEmployee(String id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    @Override
    public List<Employee> getEmployeeByFirstname(String firstname) {
        return repository.findByFirstname(firstname);
    }

    @Override
    public List<Employee> getEmployeeByLastname(String lastname) {
        return repository.findByLastname(lastname);
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (repository.existsById(employee.getId()))
            throw new EmployeeAlreadyExistException(employee);
        return repository.saveAndFlush(employee);
    }

    @Override
    public Employee editEmployee(Employee employee) {
        if (!repository.existsById(employee.getId()))
            throw new EmployeeNotFoundException(employee.getId());
        return repository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }
}
