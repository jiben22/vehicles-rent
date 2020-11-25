package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.constants.ControllerConstants.EmployeeController.*;
import fr.enssat.vehiclesrental.controller.utils.MailSender;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.enums.Position;
import fr.enssat.vehiclesrental.model.password.PasswordResetToken;
import fr.enssat.vehiclesrental.repository.PasswordResetTokenRepository;
import fr.enssat.vehiclesrental.service.EmployeeService;
import fr.enssat.vehiclesrental.service.exception.notfound.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.Controller.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.EmployeeController.*;
import static fr.enssat.vehiclesrental.constants.MailConstants.*;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping(BASE_URL)
public class EmployeeController {


    private final EmployeeService employeeService;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    /**
     * Afficher la liste des collaborateurs
     * @param springModel Modèle
     * @param firstname Prénom
     * @param lastname Nom de famille
     * @param email Email
     * @param zipcode Code postal
     * @return la liste des collaborateurs correspondant aux paramètres de requête
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @GetMapping
    public String showEmployees(Model springModel,
                                @RequestParam Optional<String> position,
                                @RequestParam(defaultValue = "") String firstname,
                                @RequestParam(defaultValue = "") String lastname,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String zipcode) {
        log.info(String.format("GET %s", BASE_URL));
        springModel.addAttribute(TITLE, GetEmployees.TITLE);

        List<Employee> employees;
        Position enumPosition = null;
        if (position.isPresent()) {
            enumPosition = Position.valueOf(position.get());
            employees = employeeService.searchEmployees(enumPosition, firstname, lastname, email, zipcode);
        } else {
            employees = employeeService.searchEmployees(enumPosition, firstname, lastname, email, zipcode);
        }

        springModel.addAttribute(EMPLOYEES, employees);

        return GetEmployees.VIEW;
    }

    /**
     * Afficher les informations d'un collaborateur
     * @param springModel Modèle
     * @param id Identifiant du collaborateur
     * @return la fiche d'un collaborateur
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @GetMapping(GetEmployeeById.URL)
    public String showEmployeeById(Model springModel, @PathVariable String id) {
        log.info(String.format("GET %s", StringUtils.replace(GetEmployeeById.URL, PATTERN_ID, id)));
        springModel.addAttribute(TITLE, GetEmployeeById.TITLE);

        Employee employee = employeeService.getEmployee(Long.parseLong(id));
        springModel.addAttribute(EMPLOYEE, employee);

        return GetEmployeeById.VIEW;
    }

    /**
     * Afficher le formulaire d'ajout d'un collaborateur
     * @param springModel Modèle
     * @return le formulaire d'ajout
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @GetMapping(AddEmployee.URL)
    public String showAddEmployee(Model springModel) {
        log.info(String.format("GET %s", AddEmployee.URL));
        springModel.addAttribute(TITLE, AddEmployee.TITLE);
        springModel.addAttribute(EMPLOYEE, new Employee());

        return AddEmployee.VIEW;
    }

    /**
     * Ajouter un collaborateur
     * @param employee Collaborateur
     * @param result Binding result
     * @param springModel Modèle
     * @param redirectAttributes Redirect attributes
     * @param request Request
     * @return la fiche du collaborateur enregistrée ou le formulaire d'ajout avec les erreurs
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @PostMapping(AddEmployee.URL)
    public String addEmployee(@Valid @ModelAttribute(EMPLOYEE) Employee employee,
                              BindingResult result,
                              Model springModel,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {
        log.info(String.format("Add employee %s", employee.getEmail()));
        springModel.addAttribute(TITLE, AddEmployee.TITLE);

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return AddEmployee.VIEW;
        }

        try {
            Employee existedEmployee = employeeService.getEmployeeByEmail(employee.getEmail());
            if (existedEmployee != null) {
                result.rejectValue("email", "employee.email",
                        "L'email est déjà attribué pour un autre collaborateur");

                // Return form with errors
                return AddEmployee.VIEW;
            }
        } catch (EmployeeNotFoundException employeeNotFoundException) {
            try {
                // Save employee
                employee = employeeService.addEmployee(employee);

                PasswordResetToken token = new PasswordResetToken();
                token.setToken(UUID.randomUUID().toString());
                token.setEmployee(employee);
                token.setExpiryDate(30);
                passwordResetTokenRepository.save(token);

                // Send mail to create password
                Map<String, String> mailContent = new HashMap<>();
                mailContent.put(RECIPIENT, employee.getEmail());
                mailContent.put(FIRSTNAME, employee.getFirstname());
                mailContent.put(SUBJECT, String.format(CreatePassword.SUBJECT, employee.getFirstname()));
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                mailContent.put(RESET_URL, String.format(CreatePassword.URL, url, token.getToken()));
                mailContent.put(TEMPLATE_ID, CreatePassword.TEMPLATE_ID);
                MailSender.sendMail(mailContent);
            } catch (Exception exception) {
                log.error(exception.getMessage() + exception.getCause());
                redirectAttributes.addFlashAttribute(MESSAGE, AddEmployee.ERROR_MESSAGE);

                return REDIRECT_EMPLOYEES;
            }
        }

        return String.format(REDIRECT_EMPLOYEE_BY_ID, employee.getId());
    }

    /**
     * Afficher le formulaire de modification d'un collaborateur
     * @param springModel Modèle
     * @param id Identifiant du collaborateur
     * @return le formulaire de modification d'un collaborateur
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @GetMapping(UpdateEmployee.URL)
    public String showUpdateEmployee(Model springModel, @PathVariable String id) {
        log.info(String.format("GET %s", StringUtils.replace(UpdateEmployee.URL, PATTERN_ID, id)));
        springModel.addAttribute(TITLE, UpdateEmployee.TITLE);

        Employee employee = employeeService.getEmployee(Long.parseLong(id));
        springModel.addAttribute(EMPLOYEE, employee);

        return UpdateEmployee.VIEW;
    }

    /**
     * Met à jour un collaborateur
     * @param employee Collaborateur
     * @param result Binding result
     * @param springModel Modèle
     * @param redirectAttributes Redirect attributes
     * @return la fiche du collaborateur mise à jour ou le formulaire de modification avec les erreurs
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @PostMapping(UpdateEmployee.URL)
    public String updateEmployee(@Valid @ModelAttribute(EMPLOYEE) Employee employee,
                                 BindingResult result,
                                 Model springModel,
                                 RedirectAttributes redirectAttributes) {
        log.info(String.format("Update employee %s", employee.getEmail()));
        springModel.addAttribute(TITLE, UpdateEmployee.TITLE);

        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return UpdateEmployee.VIEW;
        }

        try {
            // Update employee
            if (employeeService.exists(employee.getId())) {
                employee = employeeService.editEmployee(employee);
            } else {
                throw new Exception("Le collaborateur n'existe pas " + employee.getId());
            }
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, UpdateEmployee.ERROR_MESSAGE);

            return REDIRECT_EMPLOYEES;
        }

        return String.format(REDIRECT_EMPLOYEE_BY_ID, employee.getId());
    }

    /**
     * Supprimer un collaborateur
     * @param id ID du collaborateur
     * @param redirectAttributes Redirect attributes
     * @return la liste des collaborateurs ou un message d'erreur si la suppression échoue
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label)")
    @GetMapping(DeleteEmployee.URL)
    public String deleteEmployee(@PathVariable String id,
                                 RedirectAttributes redirectAttributes) {
        log.info(String.format("GET %s", StringUtils.replace(DeleteEmployee.URL, PATTERN_ID, id)));

        try {
            // Delete employee
            employeeService.deleteEmployee(Long.parseLong(id));
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, DeleteEmployee.ERROR_MESSAGE);
        }

        return REDIRECT_EMPLOYEES;
    }
}
