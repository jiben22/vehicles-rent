package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.*;
import fr.enssat.vehiclesrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employes")
    public String showEmployees(Model model) {

        log.info("GET /employes");

        model.addAttribute("title", "Liste des employés");

        // Get employees
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        //TODO: test
        log.info(employees.toString());

        return "employees";
    }
}
