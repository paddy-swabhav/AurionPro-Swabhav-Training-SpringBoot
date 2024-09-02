package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.enums.TransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class PerformTransactionDto {
	
	@Min(1)
	@Positive
	@NotNull
	private double amount;
	
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transaction type cannot be null")
	private TransactionType transactionType;
	
    @NotNull
	private long currentUserAccount;
	
	private long transferUserAccount;
	

}
