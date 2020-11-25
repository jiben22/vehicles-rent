package fr.enssat.vehiclesrental.controller.password;

import fr.enssat.vehiclesrental.model.Employee;
import fr.enssat.vehiclesrental.model.password.PasswordReset;
import fr.enssat.vehiclesrental.model.password.PasswordResetToken;
import fr.enssat.vehiclesrental.repository.PasswordResetTokenRepository;
import fr.enssat.vehiclesrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.PasswordResetController.*;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping(BASE_URL)
public class PasswordResetController {

    /**
     * Service pour les collaborateurs
     */
    private final EmployeeService employeeService;

    /**
     * Repository pour la réinitialisation du mot de passe
     */
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @ModelAttribute(PASSWORD_RESET)
    public PasswordReset passwordReset() {
        return new PasswordReset();
    }

    /**
     * Afficher la page de réinitialisation du mot de passe
     * @param token Token
     * @param model Modèle
     * @param option Option
     * @return le formulaire pour la réinitialisation du mot de passe
     */
    @GetMapping
    public String showResetPassword(@RequestParam(required = false) String token,
                                           Model model, @PathVariable String option) {

        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Token invalide.");
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        if(option.equals("new")) {
            model.addAttribute("title", "Création de mot de passe");
            model.addAttribute("description", "Choisissez votre mot de passe");
            model.addAttribute("submit", "CRÉER MON MOT DE PASSE");
            model.addAttribute("option", "new");
        } else {
            model.addAttribute("title", "Réinitialisation de mot de passe");
            model.addAttribute("description", "Entrez votre nouveau mot de passe");
            model.addAttribute("submit", "RÉINITIALISER LE MOT DE PASSE");
            model.addAttribute("option", "pwd");
        }

        return VIEW;
    }

    /**
     * Met à jour le mot de passe
     * @param form Instance de PasswordReset
     * @param result Binding result
     * @param redirectAttributes Redirect attributes
     * @param option Option
     * @return la page de connexion ou le formulaire de réinitialisation du mot de passe avec les erreurs
     */
    @PostMapping
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordReset form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes, @PathVariable String option) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return String.format(REDIRECT_URL_FAIL, option, form.getToken());
        }

        PasswordResetToken token = passwordResetTokenRepository.findByToken(form.getToken());
        Employee employee = token.getEmployee();

        employee.setPassword(form.getPassword());
        employeeService.editEmployee(employee);
        passwordResetTokenRepository.delete(token);

        return REDIRECT_URL_SUCCESS;
    }
}
