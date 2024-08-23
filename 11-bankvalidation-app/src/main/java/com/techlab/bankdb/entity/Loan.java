package com.techlab.bankdb.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanId")
    private int loanId;

    @NotNull(message = "Loan amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Loan amount must be greater than 0")
    @Column(name = "loanAmount")
    private double loanAmount;

    @NotNull(message = "Interest rate cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Interest rate must be greater than 0")
    @Column(name = "interestRate")
    private double interestRate;

    @NotNull(message = "Loan term cannot be null")
    @Min(value = 1, message = "Loan term must be at least 1 month")
    @Column(name = "loanTerm")
    private int loanTerm; // This is in months

    @NotNull(message = "Start date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name = "startDate")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name = "endDate")
    private Date endDate;

    @NotNull(message = "Loan status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "loanStatus")
    private LoanStatus loanStatus;
}
