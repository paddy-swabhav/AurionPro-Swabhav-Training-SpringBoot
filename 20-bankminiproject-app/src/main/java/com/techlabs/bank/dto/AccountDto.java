package com.techlabs.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDto {
	
	@NotNull(message = "Account Number cannot be null")
    private long accountNumber;
    

	@NotNull(message = "Balance cannot be null")
	@DecimalMin(value = "1000", inclusive = true, message = "Minimum Balance should be 1000")
	private double balance;
	
    private int customerId;
}
