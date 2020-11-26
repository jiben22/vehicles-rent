package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.enums.Position;
import fr.enssat.vehiclesrental.repository.EmployeeRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.EmployeeAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static fr.enssat.vehiclesrental.repository.EmployeeRepository.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository repository;

    @Override
    public boolean exists(long id) {
        return repository.existsById(id);
    }

    @Override
    public Employee getEmployee(long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(String.valueOf(id)));
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastname"));
    }

    @Override
    public List<Employee> searchEmployees(Position position, String firstname, String lastname, String email, String zipcode) {
        Specification<Employee> employeeSpecification = buildSpecification(position, firstname, lastname, email, zipcode);
        if (employeeSpecification != null) {
            return repository.findAll(employeeSpecification);
        } else {
            return getEmployees();
        }
    }

    private Specification<Employee> buildSpecification(Position position, String firstname, String lastname, String email, String zipcode) {
        List<Specification<Employee>> specifications = new ArrayList<>();
        if (position != null) specifications.add(hasPosition(position));
        if (!firstname.isEmpty()) specifications.add(hasFirstname(firstname));
        if (!lastname.isEmpty()) specifications.add(hasLastname(lastname));
        if (!email.isEmpty()) specifications.add(hasEmail(email));
        if (!zipcode.isEmpty()) specifications.add(hasZipcode(zipcode));

        if (specifications.size() > 0) {
            Specification<Employee> employeeSpecification = where(specifications.get(0));
            specifications.remove(0);
            for (Specification<Employee> specification: specifications) {
                employeeSpecification = Objects.requireNonNull(employeeSpecification).and(specification);
            }

            return employeeSpecification;
        } else {
            return null;
        }
    }

    @Override
    public List<Employee> searchEmployees(Position position, String firstname, String lastname, String email, String zipcode) {
        Specification<Employee> employeeSpecification = buildSpecification(position, firstname, lastname, email, zipcode);
        if (employeeSpecification != null) {
            return repository.findAll(employeeSpecification);
        } else {
            return getEmployees();
        }
    }

    private Specification<Employee> buildSpecification(Position position, String firstname, String lastname, String email, String zipcode) {
        List<Specification<Employee>> specifications = new ArrayList<>();
        if (position != null) specifications.add(hasPosition(position));
        if (!firstname.isEmpty()) specifications.add(hasFirstname(firstname));
        if (!lastname.isEmpty()) specifications.add(hasLastname(lastname));
        if (!email.isEmpty()) specifications.add(hasEmail(email));
        if (!zipcode.isEmpty()) specifications.add(hasZipcode(zipcode));

        if (specifications.size() > 0) {
            Specification<Employee> employeeSpecification = where(specifications.get(0));
            specifications.remove(0);
            for (Specification<Employee> specification: specifications) {
                employeeSpecification = Objects.requireNonNull(employeeSpecification).and(specification);
            }

            return employeeSpecification;
        } else {
            return null;
        }
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
            throw new EmployeeNotFoundException(String.valueOf(employee.getId()));
        return repository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Employee> employee = repository.findByEmail(email);
        if (employee.isEmpty()) {
            throw new UsernameNotFoundException("User mail " + email + " was not found in the database");
        }

        return new User(employee.get().getEmail(), employee.get().getPassword(), mapRolesToAuthorities(employee.get().getPosition()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Position position) {
        return Collections.singleton(new SimpleGrantedAuthority(position.label));
    }
}
