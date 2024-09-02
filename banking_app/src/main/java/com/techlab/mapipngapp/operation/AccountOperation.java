package com.techlab.mapipngapp.operation;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techlab.mapipngapp.dto.AccountDto;
import com.techlab.mapipngapp.dto.PerformTransactionDto;
import com.techlab.mapipngapp.dto.TransactionResponseDto;
import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.entity.Customer;
import com.techlab.mapipngapp.enums.TransactionType;
import com.techlab.mapipngapp.repository.AccountRepository;

@Component
public class AccountOperation {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private Account account;
	
	@Autowired
	private TransactionResponseDto transactionResponseDto;
	
	


	public long generateAccountNumber() {
	    Random random = new Random();
	    // Generate a random 12-digit number
	    long accountNumber = 100000000000L + (long)(random.nextDouble() * 900000000000L);
	    return accountNumber;
	}

	
	public Account isAccountNumberExist(long accountNumber) {
		if(accountRepository.findByAccountNumber(accountNumber) != null) {
			throw new RuntimeException("account number already exist: "+accountNumber);
		}
        return accountRepository.findByAccountNumber(accountNumber);
    }
	
	public Account isAccountExist(long accountNumber) {
		if(accountRepository.findByAccountNumber(accountNumber) == null) {
			throw new RuntimeException("account number does not exist: "+accountNumber);
		}
        return accountRepository.findByAccountNumber(accountNumber);
    }
	
	
	public Account getAccountByAccountNumber(long accountNumber) {
		if(accountRepository.findByAccountNumber(accountNumber) == null) {
			return null;
		}
        return accountRepository.findByAccountNumber(accountNumber);
    }
	
	
	public Account createAccount(long accountNumber , double balance,Customer customer) { 
		isAccountNumberExist(accountNumber);
		
		account.setAccountNumber(accountNumber);
		account.setBalance(balance);
	    account.setCustomer(customer);
	    account.setAccountId(0);
	    return accountRepository.save(account);
	}


	
	public TransactionResponseDto performTransaction(
			PerformTransactionDto transactionDto) {
		isAccountExist(transactionDto.getCurrentUserAccount());
		// TODO Auto-generated method stub
		switch (transactionDto.getTransactionType()){
		case CREDIT:
			transactionResponseDto=credit(transactionDto.getCurrentUserAccount(),transactionDto.getAmount());
			
			break;
		case TRANSFER:
			transactionResponseDto=transfer(transactionDto.getCurrentUserAccount(),transactionDto.getTransferUserAccount(),transactionDto.getAmount());
			
			break;
		case DEBIT:
			transactionResponseDto=debit(transactionDto.getCurrentUserAccount(),transactionDto.getAmount());
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + transactionDto.getTransactionType());
		}
		
		return transactionResponseDto;
		
	}
	
	private TransactionResponseDto credit(long accountNumber , double amount) {
		isAccountExist(accountNumber);
		if(amount<0) {
			throw new RuntimeException("amount need atleast 1 for credit operation");
		}
		
		Account dbAccount = accountRepository.findByAccountNumber(accountNumber);
		double prevBalance = dbAccount.getBalance();
		dbAccount.setBalance(prevBalance+amount);
		
		dbAccount=accountRepository.save(dbAccount);
		
		transactionResponseDto.setAmount(amount);
		transactionResponseDto.setCurrentBalance(dbAccount.getBalance());
		transactionResponseDto.setPrevBalance(prevBalance);
		transactionResponseDto.setTransactionType(TransactionType.CREDIT);
		return transactionResponseDto;
	}
	
	private TransactionResponseDto debit(long accountNumber , double amount) {
		isAccountExist(accountNumber);
		
		if(amount<0) {
			throw new RuntimeException("amount need atleast 1 for credit operation");
		}
		
		
		
		Account dbAccount = accountRepository.findByAccountNumber(accountNumber);
		double prevBalance = dbAccount.getBalance();
		if(prevBalance<amount) {
			throw new RuntimeException("Inssuficent Balance");
		}
		dbAccount.setBalance(prevBalance-amount);
		
		dbAccount=accountRepository.save(dbAccount);
		
		transactionResponseDto.setAmount(amount);
		transactionResponseDto.setCurrentBalance(dbAccount.getBalance());
		transactionResponseDto.setPrevBalance(prevBalance);
		transactionResponseDto.setTransactionType(TransactionType.DEBIT);
		return transactionResponseDto;
	}
	
	@Transactional
	private TransactionResponseDto transfer(long senderAccountNumber,long reciverAccountNumber , double amount) {
		isAccountExist(senderAccountNumber);
		isAccountExist(reciverAccountNumber);
		
		if(amount<0) {
			throw new RuntimeException("amount need atleast 1 for credit operation");
		}
		
		
		
		Account dbSenderAccount = accountRepository.findByAccountNumber(senderAccountNumber);
		Account dbReseverAccount = accountRepository.findByAccountNumber(reciverAccountNumber);
		
		double prevBalance = dbSenderAccount.getBalance();
		if(prevBalance<amount) {
			throw new RuntimeException("Inssuficent Balance");
		}
		dbSenderAccount.setBalance(prevBalance-amount);
		
		dbSenderAccount=accountRepository.save(dbSenderAccount);
		
		dbReseverAccount.setBalance(dbReseverAccount.getBalance()+amount);
		
		dbReseverAccount = accountRepository.save(dbReseverAccount);
		
		
		transactionResponseDto.setAmount(amount);
		transactionResponseDto.setCurrentBalance(dbSenderAccount.getBalance());
		transactionResponseDto.setPrevBalance(prevBalance);
		transactionResponseDto.setTransactionType(TransactionType.TRANSFER);
		return transactionResponseDto;
	}
	
	
	public AccountDto toAccountDtoMapper(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setBalance(account.getBalance());
		
		return accountDto;
	}

}
