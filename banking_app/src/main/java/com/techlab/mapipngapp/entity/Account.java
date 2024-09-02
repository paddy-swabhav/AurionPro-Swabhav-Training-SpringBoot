package com.techlab.mapipngapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @NotNull(message = "Account number cannot be null")
    @Column(name = "account_number", unique = true, length = 12)
    private Long accountNumber;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be positive")
    @Column(name = "balance")
    private double balance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions;

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}
    
    
}
