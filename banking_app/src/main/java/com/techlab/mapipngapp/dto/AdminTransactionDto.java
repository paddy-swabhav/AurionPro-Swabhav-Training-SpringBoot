package com.techlab.mapipngapp.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class AdminTransactionDto {
	
	private int  transactionId;
	private double amount;
	private Long senderAccountNumber;
	private Long reciverAccountNumber;
	private LocalDateTime dateTimeStamp;
	private TransactionType transactionType;
	

}
