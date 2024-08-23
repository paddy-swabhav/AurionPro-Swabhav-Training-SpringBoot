package com.techlab.bankdb.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Column(name = "customerId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @NotNull(message = "First name cannot be blank")
    @Size(min = 1, message = "First name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    @Column(name = "firstName")
    private String firstName;

    @NotNull(message = "First name cannot be blank")
    @Size(min = 1, message = "First name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;


    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "emailId")
    private String emailId; 

    @NotNull(message = "Mobile number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be a 10-digit number")
    @Column(name = "mobileNumber")
    private String mobileNumber;
 
}
