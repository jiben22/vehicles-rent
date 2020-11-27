package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.enums.Position;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IEmployeeService extends UserDetailsService {
    boolean exists(long id);
    Employee getEmployee(long id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployees();
    List<Employee> searchEmployees(Position position, String firstname, String lastname, String email, String zipcode);
    Employee addEmployee(Employee employee);
    Employee editEmployee(Employee employee);
    void deleteEmployee(long id);
}
