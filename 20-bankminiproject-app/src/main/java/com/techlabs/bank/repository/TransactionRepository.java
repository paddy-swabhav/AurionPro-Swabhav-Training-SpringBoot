package com.techlabs.bank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	Page<Transaction> findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(long senderAccountNumber, long receiverAccountNumber,Pageable pageAble);
	

}
