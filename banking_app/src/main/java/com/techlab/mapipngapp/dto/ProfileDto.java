package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.enums.TransactionType;

import jakarta.persistence.Column;
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
@Component
public class ProfileDto {

    @Size(min = 1, message = "First name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    private String firstName;


    @Size(min = 1, message = "Last name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabetic characters")
    private String lastName;


}
