package com.techlab.mapipngapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findBySenderAccountOrReceiverAccount(Account senderAccount, Account receiverAccount);
}
