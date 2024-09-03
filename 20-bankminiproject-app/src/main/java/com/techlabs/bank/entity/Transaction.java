package com.techlabs.bank.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

	@Id
	@Column(name = "transactionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transaction type is mandatory")
    private TransactionType type;

    @Column(name = "amount")
    @Min(value = 1, message = "Transaction amount must be greater than 0")
    private double amount;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "sender_account_number")
	private Account senderAccount;

	@ManyToOne
	@JoinColumn(name = "receiver_account_number")
	private Account receiverAccount;
}
