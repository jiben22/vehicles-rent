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

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, length = 45)
    @Size(min = 1, max = 45, message = "Le nom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    @NonNull
    private String lastname;

    @Column(nullable = false, length = 45)
    @Size(min = 1, max = 45, message = "Le prenom ne peut pas être vide et ne doit pas dépasser les 45 caractères !")
    @NonNull
    private String firstname;

    @Column(nullable = false)
    @Size(min = 1, max = 45, message = "La date de naissance ne peut pas être vide et doit être une date valide !")
    @NonNull
    private LocalDate birthdate;

    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "La rue ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String street;

    @Column(nullable = false, length = 16)
    @Size(min = 1, max = 16, message = "Le code postal ne peut pas être vide et ne doit pas dépasser les 16 caractères !")
    @NonNull
    private String zipcode;

    @Column(nullable = false, length = 128)
    @Size(min = 1, max = 128, message = "Le nom de la ville ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    @NonNull
    private String city;

    @Column(nullable = false, length = 128)
    @NonNull
    @Size(min = 1, max = 128, message = "Le pays ne peut pas être vide et ne doit pas dépasser les 128 caractères !")
    private String country;

    @Column(nullable = false, length = 128, unique = true)
    @NonNull
    @Email
    private String email;
}