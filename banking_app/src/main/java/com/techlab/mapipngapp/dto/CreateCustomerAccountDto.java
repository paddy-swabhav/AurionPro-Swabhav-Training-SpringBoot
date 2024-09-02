package com.techlab.mapipngapp.dto;



import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class CreateCustomerAccountDto {
	
	private int accountId;
	@NotNull
	private int customerId;
	@NotNull
	private long accountNumber;
	@Min(500)
	@Positive
	private double balance;

}
