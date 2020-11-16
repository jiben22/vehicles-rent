package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static fr.enssat.vehiclesrental.controller.constants.Constants.Controller.TITLE;
import static fr.enssat.vehiclesrental.controller.constants.Constants.EmployeeController.EMPLOYEES;
import static fr.enssat.vehiclesrental.controller.constants.Constants.EmployeeController.GetEmployees;

@RequiredArgsConstructor
@Controller
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    //TODO: pre authorize
    @GetMapping(GetEmployees.URL)
    public String getEmployees(Model model) {

        log.info(String.format("GET %s", GetEmployees.URL));
        model.addAttribute(TITLE, GetEmployees.TITLE);

        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute(EMPLOYEES, employees);

        return GetEmployees.VIEW;
    }
}
