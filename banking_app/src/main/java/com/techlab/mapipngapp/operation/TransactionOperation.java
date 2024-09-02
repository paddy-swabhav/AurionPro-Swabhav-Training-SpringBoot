package com.techlab.mapipngapp.operation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.dto.PerformTransactionDto;
import com.techlab.mapipngapp.dto.TransactionResponseDto;
import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.entity.Transaction;
import com.techlab.mapipngapp.enums.TransactionType;
import com.techlab.mapipngapp.repository.TransactionRepository;


@Component
public class TransactionOperation {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountOperation accountOperation;
	
	@Autowired
	private Transaction transaction;
	
	public Transaction addTransaction(TransactionResponseDto transactionDto,PerformTransactionDto performTransactionDto) {
		
		Account senderAccount=null;
		Account reciverAccount=null;
		if(transactionDto.getTransactionType()==TransactionType.CREDIT) {
			 reciverAccount =accountOperation.getAccountByAccountNumber(performTransactionDto.getCurrentUserAccount());
		}else if(transactionDto.getTransactionType()==TransactionType.DEBIT) {
			 senderAccount = accountOperation.getAccountByAccountNumber(performTransactionDto.getCurrentUserAccount());
		}else {
			 senderAccount = accountOperation.getAccountByAccountNumber(performTransactionDto.getCurrentUserAccount());
			 reciverAccount =accountOperation.getAccountByAccountNumber(performTransactionDto.getTransferUserAccount());
		}
		
		transaction.setAmount(transactionDto.getAmount());
		transaction.setReceiverAccount(reciverAccount);
		transaction.setSenderAccount(senderAccount);
		transaction.setTransactionType(performTransactionDto.getTransactionType());
		transaction.setDateTimeStamp(LocalDateTime.now());
		transaction.setTransactionId(0);
		return transactionRepository.save(transaction);
	}

}
