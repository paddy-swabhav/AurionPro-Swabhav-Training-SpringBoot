package com.techlabs.bank.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "account")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Account {

	@Id
	@Column(name = "accountNumber")
	private long accountNumber;
	
	@Column(name = "balance")
	private double balance;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "customerId")
	private Customer customer;
	
    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactionsSent;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactionsReceived;
}
