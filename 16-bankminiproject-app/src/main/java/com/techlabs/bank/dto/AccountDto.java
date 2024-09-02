package com.techlabs.bank.dto;

import lombok.Data;

@Data
public class AccountDto {
    private long accountNumber;
    private double balance;
    private int customerId;
}
