package com.techlab.mapipngapp.entity;

import com.techlab.mapipngapp.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Component
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Column(name = "amount")
    private double amount;

    @NotNull(message = "DateTimeStamp cannot be null")
    @PastOrPresent(message = "Transaction date and time must be in the past or present")
    @Column(name = "transaction_timestamp")
    private LocalDateTime dateTimeStamp;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transaction type cannot be null")
    @Column(name = "transaction_type")
    private TransactionType transactionType;
}
