package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.EmployeeFactory;
import fr.enssat.vehiclesrental.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @DisplayName("Get employee with an id")
    @Test
    public void findById() {
        Optional<Employee> optionalEmployee = employeeRepository.findById(157314099170601L);
        assertTrue(optionalEmployee.isPresent());
        Employee employee = optionalEmployee.get();
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeResponsableLocation(), "password").matches(employee));
    }

    @DisplayName("Get employee with an unknown id")
    @Test
    public void findByIdUnknown() {
        Optional<Employee> optionalEmployee = employeeRepository.findById(-999L);
        assertFalse(optionalEmployee.isPresent());
    }

    @DisplayName("Get employee with an email")
    @Test
    public void findByEmail() {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("tony.stark@marvel.com");
        assertTrue(optionalEmployee.isPresent());
        Employee employee = optionalEmployee.get();
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeResponsableLocation(), "password").matches(employee));
    }

    @DisplayName("Get employee with an unknown email")
    @Test
    public void testGetEmployeeByEmailException() {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("unknown@unknown.fr");
        assertFalse(optionalEmployee.isPresent());
    }

    @DisplayName("Get employee with a firstname")
    @Test
    public void findByFirstname() {
        List<Employee> employees = employeeRepository.findByFirstname("Tony");
        assertEquals(employees.size(), 1);
        employees.forEach(employee ->
                assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeResponsableLocation(), "password").matches(employee))
        );
    }

    @DisplayName("Get employee with a lastname")
    @Test
    public void findByLastname() {
        List<Employee> employees = employeeRepository.findByLastname("Stark");
        assertEquals(employees.size(), 1);
        employees.forEach(employee ->
                assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeResponsableLocation(), "password").matches(employee))
        );
    }

    @DisplayName("Get all employees")
    @Test
    public void findAll() {
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(employees.size(), 10);
    }

    @DisplayName("Create a new employee")
    @Test
    public void saveAndFlush() {
        Employee employee = EmployeeFactory.getEmployeeResponsableLocation();
        Employee addedEmploye = employeeRepository.saveAndFlush(employee);
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeResponsableLocation(), "password").matches(addedEmploye));
        assertTrue(employee.matchesPassword("Ironman12*"));
    }

    @DisplayName("Delete an employee with an id")
    @Test
    public void deleteById() {
        employeeRepository.deleteById(157314099170608L);
        assertFalse(employeeRepository.existsById(157314099170608L));
    }
}
