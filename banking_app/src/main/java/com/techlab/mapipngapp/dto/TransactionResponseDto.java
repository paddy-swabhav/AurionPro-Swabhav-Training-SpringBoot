package com.techlab.mapipngapp.dto;

import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.enums.TransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class TransactionResponseDto {

	private double amount;
	private double currentBalance;
	private double prevBalance;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
}
