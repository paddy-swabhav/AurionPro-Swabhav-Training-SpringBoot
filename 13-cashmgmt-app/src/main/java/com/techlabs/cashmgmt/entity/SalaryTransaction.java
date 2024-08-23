package com.techlabs.cashmgmt.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="salaryTransaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryTransaction {

	@Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    
    @Column(name = "transactionDate")
    @Past(message = "Transaction Date must be in the past")
    @NotNull(message = "Transaction Date cannot be null")
    private Date transactionDate;
    
    @Column(name = "amount")
    @Positive(message = "Amount must be positive")
    @NotNull(message = "Amount cannot be null")
    private double amount;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private SalaryTransactionStatus status;
}
