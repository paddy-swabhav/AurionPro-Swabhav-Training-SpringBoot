	package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.entity.TransactionType;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.TransactionRepository;
import com.techlabs.bank.service.EmailService;
import com.techlabs.bank.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    @Transactional
    public TransactionDto performTransaction(TransactionDto transactionDto) {
        Account senderAccount = accountRepository.findById(transactionDto.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender Account not found"));

        if (transactionDto.getType() == TransactionType.Credit) {
            senderAccount.setBalance(senderAccount.getBalance() + transactionDto.getAmount());
        }

        if (transactionDto.getType() == TransactionType.Debit) {
            if (senderAccount.getBalance() < transactionDto.getAmount()) {
                throw new RuntimeException("Insufficient balance");
            }
            senderAccount.setBalance(senderAccount.getBalance() - transactionDto.getAmount());
        }

        if (transactionDto.getType() == TransactionType.Transfer) {
            if (senderAccount.getBalance() < transactionDto.getAmount()) {
                throw new RuntimeException("Insufficient balance");
            }
            if (transactionDto.getAccountNumber() == transactionDto.getReceiverAccountNumber()) {
                throw new RuntimeException("Sender and receiver account numbers cannot be the same");
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
        
        TransactionDto transactionDto2 = toTransactionDto(savedTransaction);
        
        if (transactionDto.getType() == TransactionType.Credit) 
        	emailService.creditNotificationMail(transactionDto2, customerRepo.findByAccounts_AccountNumber(transactionDto.getAccountNumber()).getFirstName(), customerRepo.findByAccounts_AccountNumber(transactionDto.getAccountNumber()).getEmail());

        if (transactionDto.getType() == TransactionType.Debit)
        	emailService.debitNotificationMail(transactionDto2, customerRepo.findByAccounts_AccountNumber(transactionDto.getAccountNumber()).getFirstName(), customerRepo.findByAccounts_AccountNumber(transactionDto.getAccountNumber()).getEmail());

        if (transactionDto.getType() == TransactionType.Transfer)
        	emailService.transferNotificationMail(transactionDto2, customerRepo.findByAccounts_AccountNumber(transactionDto.getAccountNumber()).getFirstName(), customerRepo.findByAccounts_AccountNumber(transactionDto.getAccountNumber()).getEmail());

        return transactionDto2;
    }

    @Override
    public PageResponse<TransactionDto> getAllTransactions(int pageNo, int pageSize) {
    	
    	Pageable page = PageRequest.of(pageNo, pageSize); 
		Page<Transaction> transactionPage = transactionRepository.findAll(page);
       
		PageResponse<TransactionDto> transactionPageResponse = new PageResponse();
		transactionPageResponse.setTotalPages(transactionPage.getTotalPages());
		transactionPageResponse.setTotalElements(transactionPage.getTotalElements());
		transactionPageResponse.setSize(transactionPage.getSize());
		transactionPageResponse.setLastPage(transactionPage.isLast());
		
		List<TransactionDto> transactionDtos = new ArrayList<>();
		
		transactionPage.getContent().forEach((transaction)->{
			transactionDtos.add(toTransactionDto(transaction));
		});
        
		transactionPageResponse.setContent(transactionDtos);

        return transactionPageResponse;
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
    public PageResponse<TransactionDto> getTransactionsForAccount(int pageNo, int pageSize,long accountNumber) {

        Pageable page = PageRequest.of(pageNo, pageSize); 
		Page<Transaction> transactionPage = transactionRepository.findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(accountNumber, accountNumber,page);

       
		PageResponse<TransactionDto> transactionPageResponse = new PageResponse();
		transactionPageResponse.setTotalPages(transactionPage.getTotalPages());
		transactionPageResponse.setTotalElements(transactionPage.getTotalElements());
		transactionPageResponse.setSize(transactionPage.getSize());
		transactionPageResponse.setLastPage(transactionPage.isLast());
		
		List<TransactionDto> transactionDtos = new ArrayList<>();
		
		transactionPage.getContent().forEach((transaction)->{
			transactionDtos.add(toTransactionDto(transaction));
		});
        
		transactionPageResponse.setContent(transactionDtos);

        return transactionPageResponse;
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
