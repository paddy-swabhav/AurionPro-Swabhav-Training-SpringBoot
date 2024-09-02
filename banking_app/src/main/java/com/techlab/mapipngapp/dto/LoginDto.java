package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class LoginDto {
	@NotNull
	private String email;
	@NotNull
	private String password;

}
