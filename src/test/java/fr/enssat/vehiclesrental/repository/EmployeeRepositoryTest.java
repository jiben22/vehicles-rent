package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.EmployeeFactory;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

//    @Test
//    public void testGetEmployee() {
//        Optional<Employee> optionalEmployee = repository.findById("157314099170601");
//
//        assertTrue(optionalEmployee.isPresent());
//
//        Employee employee = optionalEmployee.get();
//
//        //TODO: use hamcrest
//        assertEquals(employee.getId(), "157314099170601");
//        assertEquals(employee.getLastname(), "Stark");
//        assertEquals(employee.getFirstname(), "Tony");
//        assertEquals(employee.getStreet(), "9 rue du chene germain");
//        assertEquals(employee.getZipcode(), "22700");
//        assertEquals(employee.getCity(), "Lannion");
//        assertEquals(employee.getCountry(), "France");
//        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
//        assertEquals(employee.getPosition(), Position.RESPONSABLE_LOCATION);
//        assertTrue(employee.matchesPassword("Ironman12*"));
//    }

//    @Test
//    public void testGetEmployeeException() {
//        Optional<Employee> optionalEmployee = repository.findById("UNKNOWN ID");
//
//        assertFalse(optionalEmployee.isPresent());
//    }

    @Test
    public void testGetEmployeeByEmail() {
        Optional<Employee> optionalEmployee = repository.findByEmail("tony.stark@marvel.com");

        assertTrue(optionalEmployee.isPresent());

        Employee employee = optionalEmployee.get();

        //TODO: use hamcrest
        assertEquals(employee.getId(), Long.parseLong("157314099170601"));
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getZipcode(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPhone(),"+33612482274");
        assertEquals(employee.getFunction(), Function.RESPONSABLE_LOCATION);
        assertTrue(employee.matchesPassword("Ironman12*"));
    }

    @Test
    public void testGetEmployeeByEmailException() {
        Optional<Employee> optionalEmployee = repository.findByEmail("unknown");

        assertFalse(optionalEmployee.isPresent());
    }

    @Test
    public void testGetEmployeeByFirstname() {
        List<Employee> employees = repository.findByFirstname("Tony");

        assertNotNull(employees);
        assertNotEquals(employees.size(), 0);

        Employee employee = employees.get(0);

        //TODO: use hamcrest
        assertEquals(employee.getId(), Long.parseLong("157314099170601"));
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getZipcode(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPhone(),"+33612482274");
        assertEquals(employee.getFunction(), Function.RESPONSABLE_LOCATION);
        assertTrue(employee.matchesPassword("Ironman12*"));
    }

    @Test
    public void testGetEmployeeByLastname() {
        List<Employee> employees = repository.findByLastname("Stark");

        assertNotNull(employees);
        assertNotEquals(employees.size(), 0);

        Employee employee = employees.get(0);

        //TODO: use hamcrest
        assertEquals(employee.getId(), Long.parseLong("157314099170601"));
        assertEquals(employee.getLastname(), "Stark");
        assertEquals(employee.getFirstname(), "Tony");
        assertEquals(employee.getStreet(), "9 rue du chene germain");
        assertEquals(employee.getZipcode(), "22700");
        assertEquals(employee.getCity(), "Lannion");
        assertEquals(employee.getCountry(), "France");
        assertEquals(employee.getEmail(), "tony.stark@marvel.com");
        assertEquals(employee.getPhone(),"+33612482274");
        assertEquals(employee.getFunction(), Function.RESPONSABLE_LOCATION);
        assertTrue(employee.matchesPassword("Ironman12*"));
    }

    @Test
    public void testGetEmployees() {
        List<Employee> employee_list = repository.findAll();

        assertNotNull(employee_list);
        assertNotEquals(employee_list.size(), 0);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = EmployeeFactory.getEmployeeResponsableLocation();
        Employee addedEmploye = repository.saveAndFlush(employee);

        assertThat(employee).isEqualToComparingFieldByField(addedEmploye);
        assertTrue(employee.matchesPassword("Ironman12*"));
    }

//    @Test
//    public void testDeleteEmployeeById() {
//        repository.deleteById("157314099170608");
//
//        assertFalse(repository.existsById("157314099170608"));
//    }
}
