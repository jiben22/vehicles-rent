package fr.enssat.vehiclesrental.controller.password;

import fr.enssat.vehiclesrental.controller.utils.MailSender;
import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.password.PasswordForgot;
import fr.enssat.vehiclesrental.model.password.PasswordResetToken;
import fr.enssat.vehiclesrental.repository.PasswordResetTokenRepository;
import fr.enssat.vehiclesrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reinitialisation-mot-de-passe")
public class PasswordForgotController {

    private final EmployeeService employeeService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgot forgotPasswordDto() {
        return new PasswordForgot();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgot form,
                                            BindingResult result,
                                            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "forgotPassword";
        }
        Employee employee = employeeService.getEmployeeByEmail(form.getEmail());
        if (employee == null) {
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgotPassword";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setEmployee(employee);
        token.setExpiryDate(30);
        passwordResetTokenRepository.save(token);

        Map <String, String> mailContent = new HashMap<>();
        mailContent.put("recipient", employee.getEmail());
        mailContent.put("firstname", employee.getFirstname());
        mailContent.put("subject", "Vehi'Rental - Réinitialisation du mot de passe");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        mailContent.put("resetUrl", url + "/resetPassword/pwd?token=" + token.getToken());
        mailContent.put("templateId", "1894529");

        MailSender.sendMail(mailContent);

        return "redirect:/";
    }
}
