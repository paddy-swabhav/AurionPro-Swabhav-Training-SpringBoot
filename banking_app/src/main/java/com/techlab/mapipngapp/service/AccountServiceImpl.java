package com.techlab.mapipngapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.dto.AccountDto;
import com.techlab.mapipngapp.dto.PerformTransactionDto;
import com.techlab.mapipngapp.dto.TransactionResponseDto;
import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.operation.AccountOperation;
import com.techlab.mapipngapp.operation.CustomerOperation;
import com.techlab.mapipngapp.operation.TransactionOperation;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountOperation accountOperation;
	
	@Autowired
	private TransactionOperation transactionOperation;
	
	@Autowired
	private CustomerOperation customerOperation;

	@Override
	public long genereateAccountNumber() {
		// TODO Auto-generated method stub
		return accountOperation.generateAccountNumber();
	}

	@Override
	public TransactionResponseDto performTransaction(PerformTransactionDto transactionDto) {
		// TODO Auto-generated method stub
		
		TransactionResponseDto transactionResponseDto= accountOperation.performTransaction(transactionDto);
		
		transactionOperation.addTransaction(transactionResponseDto, transactionDto);
		
		return transactionResponseDto;
	}

	@Override
	public AccountDto getAccountByAccountNumber(long accountNumber) {
		// TODO Auto-generated method stub
		
		return accountOperation.toAccountDtoMapper(accountOperation.isAccountExist(accountNumber));
	}

	@Override
	public List<AccountDto> getAllAccountByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		
		List<AccountDto> accountDtos =new ArrayList<>();
		
		List<Account> accounts= customerOperation.isCustomerExist(customerId).getAccounts();
		
		if(accounts==null || accounts.size()<1) {
			throw new RuntimeException("No account Exists");
		}
		
		accounts.forEach((account)->{
			accountDtos.add(accountOperation.toAccountDtoMapper(account));
		});
		return accountDtos;
	}

}
