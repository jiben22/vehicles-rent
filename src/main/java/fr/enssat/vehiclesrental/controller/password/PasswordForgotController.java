package fr.enssat.vehiclesrental.controller.password;

import fr.enssat.vehiclesrental.constants.ControllerConstants;
import fr.enssat.vehiclesrental.controller.utils.MailSender;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.password.PasswordForgot;
import fr.enssat.vehiclesrental.model.password.PasswordResetToken;
import fr.enssat.vehiclesrental.repository.PasswordResetTokenRepository;
import fr.enssat.vehiclesrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.DashboardController.GetDashboard.REDIRECT_URL;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.DashboardController.GetDashboard.TITLE;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.PasswordForgotController.*;
import static fr.enssat.vehiclesrental.constants.MailConstants.*;

@RequiredArgsConstructor
@Controller
@RequestMapping(BASE_URL)
public class PasswordForgotController {

    /**
     * Service pour les collaborateurs
     */
    private final EmployeeService employeeService;

    /**
     * Repository pour la réinitialisation d'un mot de passe
     */
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @ModelAttribute(PASSWORD_FORGOT)
    public PasswordForgot forgotPasswordDto() {
        return new PasswordForgot();
    }

    /**
     * Afficher la page de de demande du mot de passe
     * @return le formulaire pour la demande du mot de passe
     */
    @GetMapping
    public String showForgotPassword(Model springModel) {
        springModel.addAttribute(TITLE, ControllerConstants.PasswordForgotController.TITLE);
        return VIEW;
    }

    /**
     * Envoie un mail de demande du mot de passe
     * @param form Instance de PasswordForgot
     * @param result Binding result
     * @param request Requête
     * @return la page de connexion
     */
    @PostMapping
    public String processForgotPassword(@ModelAttribute(PASSWORD_FORGOT) @Valid PasswordForgot form,
                                            BindingResult result,
                                            HttpServletRequest request) {
        if (result.hasErrors()) {
            return VIEW;
        }
        Employee employee = employeeService.getEmployeeByEmail(form.getEmail());
        if (employee == null) {
            result.rejectValue("email", null, "L'email n'est attribué à aucun compte");
            return VIEW;
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setEmployee(employee);
        token.setExpiryDate(30);
        passwordResetTokenRepository.save(token);

        Map <String, String> mailContent = new HashMap<>();
        mailContent.put(RECIPIENT, employee.getEmail());
        mailContent.put(FIRSTNAME, employee.getFirstname());
        mailContent.put(SUBJECT, ResetPassword.SUBJECT);
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        mailContent.put(RESET_URL, String.format(ResetPassword.URL, url, token.getToken()));
        mailContent.put(TEMPLATE_ID, ResetPassword.TEMPLATE_ID);

        MailSender.sendMail(mailContent);

        return REDIRECT_URL;
    }
}
