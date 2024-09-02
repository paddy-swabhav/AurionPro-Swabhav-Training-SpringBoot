package com.techlab.mapipngapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileId;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 1, message = "First name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, message = "Last name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabetic characters")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email_id", unique = true)
    private String emailId;

}
