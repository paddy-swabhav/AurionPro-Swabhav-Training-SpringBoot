package com.techlab.mapipngapp.service;

import java.util.List;

import com.techlab.mapipngapp.dto.AccountDto;
import com.techlab.mapipngapp.dto.PerformTransactionDto;
import com.techlab.mapipngapp.dto.TransactionResponseDto;

public interface AccountService {
	
	long genereateAccountNumber();
	
	TransactionResponseDto performTransaction(PerformTransactionDto transactionDto);
	
	AccountDto getAccountByAccountNumber(long accountNumber);
	
	List<AccountDto> getAllAccountByCustomerId(int customerId);

}
