package fr.enssat.vehiclesrental.model;

import fr.enssat.vehiclesrental.model.enums.Position;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represent an employee hold in the database.
 */
@Entity
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person implements Serializable {

    /**
     * Represent which position an employee own in the company
     */
    @Column(nullable = false, length = 45)
    @NonNull
    private Position position;

    /**
     * Password use by employee to be connect on website
     */
    @ToString.Exclude
    private String password;

    /**
     * Constructor of an Employee
     *
     */
    @Builder
    private Employee(long id, String lastname, String firstname, LocalDate birthdate, String street, String zipcode, String city, String country, String email, String phone, Position position, String password) {
        super(id, lastname, firstname, birthdate, street, zipcode, city, country, email,phone);
        this.position = position;
        this.password = password;
    }

    /**
     * Override Lombok Setter to encode password
     *
     * @param password Password of the employee to encode
     */
    public void setPassword(String password) {
        this.password = encodePassword(password);
    }

    /**
     * Override Builder 'password' position to encode password
     */
    public static class EmployeeBuilder {
        private String password;

        /**
         * @param password Password to encode
         * @return Builder to encode password
         */
        public EmployeeBuilder password(String password) {
            this.password = encodePassword(password);
            return this;
        }
    }

    /**
     * Used to encode a password
     */
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Encode a password
     *
     * @param password Password to encode
     * @return Encoded password
     */
    public static String encodePassword(@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{10,})", message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un nombre et un caractère spécial (!@#$%^&*). Il doit avoir une taille minimum de 10 caractères !") String password) {
        return encoder.encode(password);
    }

    /**
     *
     * Check that the password matches with password of the employee
     *
     * @param password Password to check
     * @return password match or not
     */
    public boolean matchesPassword(String password) {
        return encoder.matches(password, this.password);
    }
}