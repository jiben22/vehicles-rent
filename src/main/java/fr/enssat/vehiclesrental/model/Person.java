package fr.enssat.vehiclesrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represent a Person used to create employee and customer.
 * There is no table Person in database
 *
 * @see Employee
 */
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Person implements Serializable {

    /**
     * Id of a person used to find her in database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Lastname of a person
     */
    @Column(nullable = false, length = 45)
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    @NonNull
    private String lastname;

    /**
     * Firstname of a person
     */
    @Column(nullable = false, length = 45)
    @Size(min = 1, max = 45, message = "Le prenom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    @NonNull
    private String firstname;

    /**
     * Birthdate of a person. Used to know age of a person.
     */
    @Column(nullable = false)
    @Size(min = 1, max = 45, message = "La date de naissance ne peut pas être vide et doit être une date valide !")
    @NonNull
    private LocalDate birthdate;

    /**
     * Name of the street where live a person
     */
    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "La rue ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String street;

    /**
     * Zipcode of the home of a person
     */
    @Column(nullable = false, length = 16)
    @Size(min = 1, max = 16, message = "Le code postal ne peut pas être vide et ne doit pas dépasser les 16 caractères !")
    @NonNull
    private String zipcode;

    /**
     * City of the home of a person
     */
    @Column(nullable = false, length = 128)
    @Size(min = 1, max = 128, message = "Le nom de la ville ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    @NonNull
    private String city;

    /**
     * Country where live a person
     */
    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "Le pays ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String country;

    /**
     * Email of a person
     */
    @Column(nullable = false, length = 128, unique = true)
    @NonNull
    @Email
    private String email;

    /**
     * Phone number of a person
     */
    @Column(nullable = false,length = 20)
    @NonNull
    @Size(min = 1, max = 20, message = "Le numéro de téléphone ne peut pas être vide et ne doit pas dépasser les 20 caractères !")
    private String phone;
}