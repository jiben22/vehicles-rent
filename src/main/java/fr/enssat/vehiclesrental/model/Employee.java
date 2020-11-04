package fr.enssat.vehiclesrental.model;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends Person implements Serializable {

    @Builder
    private Employee(Integer id, String lastname, String firstname, LocalDate birthdate, String street, String zipcode, String city, String country, String email, Position position, String password) {
        super(id, lastname, firstname, birthdate, street, zipcode, city, country, email);
        this.position = position;
        this.password = password;
    }

    @Column(nullable = false, length = 45)
    @Size(max = 45, message = "La fonction ne doit pas dépasser les 45 caractères !")
    @NonNull
    private Position position;

    @ToString.Exclude
    private String password;

    // Override Lombok Setter to encode password
    public void setPassword(String password) {
        this.password = encodePassword(password);
    }

    // Override Builder 'password' function to encode password
    public static class EmployeeBuilder {
        private String password;

        public EmployeeBuilder password(String password) {
            this.password = encodePassword(password);
            return this;
        }
    }
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // To encode password
    public static String encodePassword(@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{10,})", message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un nombre et un caractère spécial (!@#$%^&*). Il doit avoir une taille minimum de 10 caractères !") String password) {
        return encoder.encode(password);
    }

    // To check that the password matches
    public boolean matchesPassword(String password) {
        return encoder.matches(password, this.password);
    }
}