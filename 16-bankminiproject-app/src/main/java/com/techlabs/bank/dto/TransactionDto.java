package com.techlabs.bank.dto;

import com.techlabs.bank.entity.TransactionType;
import lombok.Data;

import java.sql.Date;

@Data
public class TransactionDto {
    private int transactionId;
    private TransactionType type;
    private double amount;
    private long accountNumber;
    private long receiverAccountNumber;
    private Date date;
}
