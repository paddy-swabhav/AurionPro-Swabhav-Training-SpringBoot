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
public class CustomerAccountDto {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private long accountNumber;
	private double balance;
	@Override
	public String toString() {
		return "CustomerAccountDto [firstName=" + firstName + ", lastName=" + lastName + ", accountNumber="
				+ accountNumber + ", balance=" + balance + "]";
	}
	
	

}
