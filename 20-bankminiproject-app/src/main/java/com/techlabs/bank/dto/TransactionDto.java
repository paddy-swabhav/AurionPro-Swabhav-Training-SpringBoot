package com.techlabs.bank.dto;

import com.techlabs.bank.entity.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class TransactionDto {
	
    private int transactionId;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transaction type is mandatory")
    private TransactionType type;


    @Min(value = 1, message = "Transaction amount must be greater than 0")
    private double amount;
    
    private long accountNumber;
    private long receiverAccountNumber;
    private Date date;
}
