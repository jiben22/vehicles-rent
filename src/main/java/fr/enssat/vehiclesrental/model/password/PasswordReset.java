package fr.enssat.vehiclesrental.model.password;

import fr.enssat.vehiclesrental.constraint.FieldMatch;
import fr.enssat.vehiclesrental.constraint.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Les mots de passe doivent correspondre")
public class PasswordReset {
    @ValidPassword
    @NotEmpty
    private String password;
    @ValidPassword
    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

}