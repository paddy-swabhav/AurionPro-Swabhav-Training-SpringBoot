	package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.entity.TransactionType;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.TransactionRepository;
import com.techlabs.bank.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public TransactionDto performTransaction(TransactionDto transactionDto) {
    	
        Account senderAccount = accountRepository.findById(transactionDto.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender Account not found"));

        if (transactionDto.getType() == TransactionType.Credit) 
        {
            senderAccount.setBalance(senderAccount.getBalance() + transactionDto.getAmount());
        } 
       
        else if (transactionDto.getType() == TransactionType.Debit) 
        {
            if (senderAccount.getBalance() < transactionDto.getAmount()) 
            {
                throw new RuntimeException("Insufficient balance");
            }
            senderAccount.setBalance(senderAccount.getBalance() - transactionDto.getAmount());
        } 
       
        else if (transactionDto.getType() == TransactionType.Transfer) 
        {
            if (senderAccount.getBalance() < transactionDto.getAmount())
            {
                throw new RuntimeException("Insufficient balance");
            }
            Account receiverAccount = accountRepository.findById(transactionDto.getReceiverAccountNumber())
                    .orElseThrow(() -> new RuntimeException("Receiver Account not found"));
            senderAccount.setBalance(senderAccount.getBalance() - transactionDto.getAmount());
            receiverAccount.setBalance(receiverAccount.getBalance() + transactionDto.getAmount());
            accountRepository.save(receiverAccount);
        }

        accountRepository.save(senderAccount);

        Transaction transaction = toTransaction(transactionDto, senderAccount);
        
        // Set the transaction date to the current system date
        transaction.setDate(new java.sql.Date(System.currentTimeMillis()));
        
        Transaction savedTransaction = transactionRepository.save(transaction);

        return toTransactionDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        transactions.forEach(transaction -> transactionDtos.add(toTransactionDto(transaction)));
        return transactionDtos;
    }

//    @Override
//    public List<TransactionDto> getTransactionsForAccount(long accountNumber) {
//        List<Transaction> transactions = transactionRepository.findAll();
//        List<TransactionDto> transactionDtos = new ArrayList<>();
//        transactions.forEach(transaction -> {
//            if (transaction.getSenderAccount().getAccountNumber() == accountNumber ||
//                (transaction.getReceiverAccount() != null && transaction.getReceiverAccount().getAccountNumber() == accountNumber)) {
//                transactionDtos.add(toTransactionDto(transaction));
//            }
//        });
//        return transactionDtos;
//    }
    
    @Override
    public List<TransactionDto> getTransactionsForAccount(long accountNumber) {
        List<Transaction> transactions = transactionRepository.findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(accountNumber, accountNumber);
        List<TransactionDto> transactionDtos = new ArrayList<>();
        transactions.forEach(transaction -> transactionDtos.add(toTransactionDto(transaction)));
        return transactionDtos;
    }
    
    
    
    
    // Mapper methods
    private TransactionDto toTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setType(transaction.getType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setAccountNumber(transaction.getSenderAccount().getAccountNumber());

        if (transaction.getReceiverAccount() != null) {
            transactionDto.setReceiverAccountNumber(transaction.getReceiverAccount().getAccountNumber());
        }

        transactionDto.setDate(transaction.getDate());
        return transactionDto;
    }

    private Transaction toTransaction(TransactionDto transactionDto, Account senderAccount) {
        Transaction transaction = new Transaction();
        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setSenderAccount(senderAccount);

        if (transactionDto.getType() == TransactionType.Transfer) {
            Account receiverAccount = accountRepository.findById(transactionDto.getReceiverAccountNumber())
                    .orElseThrow(() -> new RuntimeException("Receiver Account not found"));
            transaction.setReceiverAccount(receiverAccount);
        }

        transaction.setDate(transactionDto.getDate());
        return transaction;
    }
}
