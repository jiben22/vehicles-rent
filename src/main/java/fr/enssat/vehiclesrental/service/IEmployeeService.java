package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IEmployeeService extends UserDetailsService {
    boolean exists(String id);
    Employee getEmployee(String id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployeeByFirstname(String firstname);
    List<Employee> getEmployeeByLastname(String lastname);
    List<Employee> getEmployees();
    Employee addEmployee(Employee employee);
    Employee editEmployee(Employee employee);
    void deleteEmployee(String id);
}
