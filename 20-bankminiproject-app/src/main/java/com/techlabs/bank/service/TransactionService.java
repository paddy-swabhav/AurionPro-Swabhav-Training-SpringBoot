package com.techlabs.bank.service;

import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.dto.TransactionDto;

public interface TransactionService {
	
    TransactionDto performTransaction(TransactionDto transactionDto);
    
    PageResponse<TransactionDto> getAllTransactions(int pagenumber, int pagesize);
    
//    List<TransactionDto> getTransactionsForAccount(long accountNumber);
    
    public PageResponse<TransactionDto> getTransactionsForAccount(int pagesize,int pagenumber,long accountNumber);
}
