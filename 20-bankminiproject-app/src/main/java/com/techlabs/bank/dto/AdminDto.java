package com.techlabs.bank.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdminDto {
	
	private int adminId;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
	private String email;

    
	@NotBlank(message = "Name is mandatory")
	private String name;
}