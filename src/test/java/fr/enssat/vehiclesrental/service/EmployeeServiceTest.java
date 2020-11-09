package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.factory.EmployeeFactory;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.repository.EmployeeRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.EmployeeAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.EmployeeNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @DisplayName("Get employee with an id")
    @Test
    public void getEmployee() {
        when(employeeRepository.findById(anyLong()))
                .thenReturn(ofNullable(EmployeeFactory.getEmployeeGestionnaireCommercial()));

        Employee employee = employeeService.getEmployee(157314099170602L);
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeGestionnaireCommercial(), "password").matches(employee));
    }

    @DisplayName("Get employee with an unknown id")
    @Test()
    public void getEmployeeException() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(-999));
    }

    @DisplayName("Get employee with an email")
    @Test
    public void getEmployeeByEmail() {
        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(ofNullable(EmployeeFactory.getEmployeeGestionnaireCommercial()));

        Employee employee = employeeService.getEmployeeByEmail(EmployeeFactory.getEmployeeGestionnaireCommercial().getEmail());
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeGestionnaireCommercial(), "password").matches(employee));
    }

    @DisplayName("Get employee with an unknown email")
    @Test
    public void getEmployeeByEmailException() {
        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeByEmail("unknown@unknown.fr"));
    }

    @DisplayName("Get employee with a firstname")
    @Test
    public void getEmployeeByFirstname() {
        when(employeeRepository.findByFirstname(anyString()))
                .thenReturn(Collections.singletonList(EmployeeFactory.getEmployeeGestionnaireTechnique()));

        List<Employee> employees = employeeService.getEmployeeByFirstname("Henry");
        employees.forEach(employee ->
                assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeGestionnaireTechnique(), "password").matches(employee))
        );
    }

    @DisplayName("Get employee with a lastname")
    @Test
    public void getEmployeeByLastname() {
        when(employeeRepository.findByLastname(anyString()))
                .thenReturn(Collections.singletonList(EmployeeFactory.getEmployeeGestionnaireTechnique()));

        List<Employee> employees = employeeService.getEmployeeByLastname("Jonathan");
        employees.forEach(employee ->
            assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeGestionnaireTechnique(), "password").matches(employee))
        );
    }

    @DisplayName("Get all employees")
    @Test
    public void getEmployees() {
        List<Employee> allEmployees = Arrays.asList(EmployeeFactory.getEmployeeResponsableLocation(),
                EmployeeFactory.getEmployeeGestionnaireCommercial(),
                EmployeeFactory.getEmployeeGestionnaireTechnique(),
                EmployeeFactory.getEmployeeCollaborateur());

        when(employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "lastname")))
                .thenReturn(allEmployees);

        List<Employee> employees = employeeService.getEmployees();
        AtomicInteger index = new AtomicInteger();
        employees.forEach(employee ->
            assertTrue(new ReflectionEquals(allEmployees.get(index.getAndIncrement()), "password").matches(employee))
        );
    }

    @DisplayName("Create a new employee")
    @Test
    public void addEmployee() {
        when(employeeRepository.saveAndFlush(any(Employee.class))).thenReturn(EmployeeFactory.getEmployeeCollaborateur());
        when(employeeRepository.existsById(anyLong())).thenReturn(false);

        Employee employee = employeeService.addEmployee(EmployeeFactory.getEmployeeCollaborateur());
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeCollaborateur(), "password").matches(employee));
    }

    @DisplayName("Create an employee who already exists")
    @Test
    public void addEmployeeAlreadyExists() {
        when(employeeRepository.existsById(anyLong())).thenReturn(true);
        assertThrows(EmployeeAlreadyExistException.class, () -> employeeService.addEmployee(EmployeeFactory.getEmployeeCollaborateur()));
    }

    @DisplayName("Update an employee")
    @Test
    public void editEmployee() {
        when(employeeRepository.saveAndFlush(any(Employee.class))).thenReturn(EmployeeFactory.getEmployeeResponsableLocation());
        when(employeeRepository.existsById(anyLong())).thenReturn(true);

        Employee employee = employeeService.editEmployee(EmployeeFactory.getEmployeeResponsableLocation());
        assertTrue(new ReflectionEquals(EmployeeFactory.getEmployeeResponsableLocation(), "password").matches(employee));
    }

    @DisplayName("Update an unknown employee")
    @Test
    public void editEmployeeException() {
        when(employeeRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.editEmployee(EmployeeFactory.getEmployeeResponsableLocation()));
    }

    @DisplayName("Delete an employee")
    @Test
    public void deleteEmploye() {
        employeeService.deleteEmployee(4);
        assertFalse(employeeService.exists(4));
    }

    @DisplayName("Load an user with an email")
    @Test
    public void loadUserByUsername() {
        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(ofNullable(EmployeeFactory.getEmployeeResponsableLocation()));

        UserDetails userDetails = employeeService.loadUserByUsername(EmployeeFactory.getEmployeeResponsableLocation().getEmail());
        assertNotNull(userDetails.getUsername());
        assertNotNull(userDetails.getPassword());
    }

    @DisplayName("Load an user with an unknown email")
    @Test
    public void loadUserByUsernameException() {
        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> employeeService.loadUserByUsername(EmployeeFactory.getEmployeeResponsableLocation().getEmail()));
    }
}
