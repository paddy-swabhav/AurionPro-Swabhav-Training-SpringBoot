package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class CustomerDetailDto {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private int totalNumberOfAccounts;

}
