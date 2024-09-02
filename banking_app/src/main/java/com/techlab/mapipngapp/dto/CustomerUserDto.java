package com.techlab.mapipngapp.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.enums.Role;
import com.techlab.mapipngapp.enums.TransactionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CustomerUserDto {
	

	
	@NotBlank(message = "Password cannot be blank")
    private String password; 
	
	@Size(min = 8, message = "Password must have at least 8 characters")
	private String newPassword;


	 @Size(min = 1, message = "First name must have at least 1 character")
	    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
	    private String firstName;


	    @Size(min = 1, message = "Last name must have at least 1 character")
	    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabetic characters")
	    private String lastName;
    
    

}
