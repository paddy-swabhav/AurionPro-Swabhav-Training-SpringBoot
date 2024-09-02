package com.techlab.mapipngapp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.common.methods.CreateResponse;
import com.techlab.mapipngapp.dto.AdminTransactionDto;
import com.techlab.mapipngapp.dto.CustomerTransactionDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.entity.Transaction;
import com.techlab.mapipngapp.enums.TransactionType;
import com.techlab.mapipngapp.operation.AccountOperation;
import com.techlab.mapipngapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountOperation accountOperation;


	@Override
    public PageResponse<AdminTransactionDto> getAllTransactions(int pageNo, int pageSize) {
        // Create pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        
        // Fetch page of Transaction entities
        Page<Transaction> page = transactionRepository.findAll(pageable);
        
        // Convert Transaction entities to AdminTransactionDto using Java streams
        Page<AdminTransactionDto> transactionDtos = page.map(transaction -> 
            Stream.of(transaction)
                  .map(this::toAdminTransactionDtoMapper)
                  .findFirst()
                  .orElse(null)
        );
        
        CreateResponse<AdminTransactionDto> createResponse=new CreateResponse<>();
        
        // Return the response wrapped in PageResponse
        return createResponse.createPageResponse(transactionDtos);
    }

	@Override
	public PageResponse<CustomerTransactionDto> getTransactionByAccount(long accountNumber, int pageNo, int pageSize) {
	    // Fetch account by account number
	    Account account = accountOperation.getAccountByAccountNumber(accountNumber);
	    
	    // Fetch transactions where the account is either sender or receiver
	    List<Transaction> transactions = transactionRepository.findBySenderAccountOrReceiverAccount(account, account);
	    
	    // Map transactions to DTOs
	    List<CustomerTransactionDto> transactionDtos = transactions.stream()
	        .map(transaction -> toCustomerTransactionDtoMapper(transaction, account))
	        .collect(Collectors.toList());

	    // Calculate pagination details
	    int totalElements = transactionDtos.size();
	    int fromIndex = pageNo * pageSize;
	    int toIndex = Math.min(fromIndex + pageSize, totalElements);
	    List<CustomerTransactionDto> pagedTransactions = transactionDtos.subList(fromIndex, toIndex);
	    
	    // Create PageResponse
	    PageResponse<CustomerTransactionDto> pageResponse = new PageResponse<>();
	    pageResponse.setTotalPages((totalElements + pageSize - 1) / pageSize);  // Calculate total pages
	    pageResponse.setTotalElements(totalElements);
	    pageResponse.setSize(pagedTransactions.size());
	    pageResponse.setContent(pagedTransactions);
	    pageResponse.setLastPage(pageNo == pageResponse.getTotalPages() - 1);  // Check if it's the last page
	    
	    return pageResponse;
	}

	
	private AdminTransactionDto toAdminTransactionDtoMapper(Transaction transaction) {
		AdminTransactionDto transactionDto = new AdminTransactionDto();
		
		transactionDto.setAmount(transaction.getAmount());
		transactionDto.setDateTimeStamp(transaction.getDateTimeStamp());
		transactionDto.setReciverAccountNumber(getAccountNumber(transaction.getReceiverAccount()));
		transactionDto.setSenderAccountNumber(getAccountNumber(transaction.getSenderAccount()));
		transactionDto.setTransactionType(transaction.getTransactionType());
		transactionDto.setTransactionId(transaction.getTransactionId());
		
		return transactionDto;
		
	}
	
	private CustomerTransactionDto toCustomerTransactionDtoMapper(Transaction transaction,Account account) {
		CustomerTransactionDto transactionDto = new CustomerTransactionDto();
		
		if(transaction.getTransactionType()==TransactionType.TRANSFER) {
			 if(transaction.getSenderAccount().getAccountNumber()==account.getAccountNumber()) {
				 transactionDto.setTransactionType(TransactionType.DEBIT);
				 transactionDto.setRecipientAccount(transaction.getReceiverAccount().getAccountNumber());
			 }else {
			 
			 transactionDto.setTransactionType(TransactionType.CREDIT);
			 transactionDto.setRecipientAccount(transaction.getSenderAccount().getAccountNumber());
			 }
		}else {
			transactionDto.setTransactionType(transaction.getTransactionType());
			transactionDto.setRecipientAccount(null);
		}
		 transactionDto.setAmount(transaction.getAmount());
	     transactionDto.setDateTimeStamp(transaction.getDateTimeStamp());
		 transactionDto.setTransactionId(transaction.getTransactionId());
		 
		 return transactionDto;
	}
	
	private Long getAccountNumber(Account account) {
		if(account!=null) {
			return account.getAccountNumber();
		}
		return null;
	}



}
