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
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private int paymentId;

    @NotNull(message = "Payment date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name = "paymentDate")
    private Date paymentDate;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Column(name = "amount")
    private double amount;

    @NotNull(message = "Payment mode cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMode")
    private PaymentMode paymentMode;

    @NotNull(message = "Payment status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentStatus")
    private PaymentStatus paymentStatus;
}
