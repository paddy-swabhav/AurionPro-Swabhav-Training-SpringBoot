package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	List<Transaction> findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(long senderAccountNumber, long receiverAccountNumber);
	

}
