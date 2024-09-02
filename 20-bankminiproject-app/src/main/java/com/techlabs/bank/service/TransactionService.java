package com.techlabs.bank.service;

import java.util.List;

import com.techlabs.bank.dto.TransactionDto;

public interface TransactionService {
    TransactionDto performTransaction(TransactionDto transactionDto);
    List<TransactionDto> getAllTransactions();
//    List<TransactionDto> getTransactionsForAccount(long accountNumber);
    public List<TransactionDto> getTransactionsForAccount(long accountNumber);
}
