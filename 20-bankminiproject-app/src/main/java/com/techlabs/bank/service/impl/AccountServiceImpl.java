package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.CustomerAccountsDto;
import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        // Generate a unique 12-digit account number
        long accountNumber = generateUniqueAccountNumber();

        // Fetch the customer by ID
        Optional<Customer> customerOptional = customerRepository.findById(accountDto.getCustomerId());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            // Create a new Account
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setBalance(accountDto.getBalance());
            account.setCustomer(customer);

            // Save the account
            Account savedAccount = accountRepository.save(account);

            // Return the DTO
            return toAccountDto(savedAccount);
        } else {
            throw new CustomerNotFoundException(accountDto.getCustomerId());
        }
    }

    private long generateUniqueAccountNumber() {
        Random random = new Random();
        long accountNumber;
        do {
            accountNumber = 100000000000L + (long) (random.nextDouble() * 899999999999L);
        } while (accountRepository.existsById(accountNumber));
        return accountNumber;
    }

    @Override
    public PageResponse<AccountDto> getAllAccounts(int pageNo, int pageSize) {
    	
		Pageable page = PageRequest.of(pageNo, pageSize); 
		Page<Account> accountPage = accountRepository.findAll(page);
       
		PageResponse<AccountDto> accountPageResponse = new PageResponse();
		accountPageResponse.setTotalPages(accountPage.getTotalPages());
		accountPageResponse.setTotalElements(accountPage.getTotalElements());
		accountPageResponse.setSize(accountPage.getSize());
		accountPageResponse.setLastPage(accountPage.isLast());
		
		List<AccountDto> accountDtos = new ArrayList<>();
		
		accountPage.getContent().forEach((account)->{
			accountDtos.add(toAccountDto(account));
		});
        
		accountPageResponse.setContent(accountDtos);

        return accountPageResponse;
    }
    
    
    @Override
    public PageResponse<CustomerAccountsDto> getAccountsForCustomer(int pageNo, int pageSize, int customerId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Account> accountPage = accountRepository.findByCustomer_CustomerId(customerId, pageable);

        PageResponse<CustomerAccountsDto> accountPageResponse = new PageResponse<>();
        accountPageResponse.setTotalPages(accountPage.getTotalPages());
        accountPageResponse.setTotalElements(accountPage.getTotalElements());
        accountPageResponse.setSize(accountPage.getSize());
        accountPageResponse.setLastPage(accountPage.isLast());

        if (!accountPage.isEmpty()) {
            CustomerAccountsDto dto = new CustomerAccountsDto();
            dto.setCustomerId(customerId);
            dto.setNumberOfAccounts(accountPage.getTotalElements());
            List<AccountDto> accountDtos = new ArrayList<>();
            
            accountPage.getContent().forEach(account -> {
                accountDtos.add(toAccountDto(account));
            });

            dto.setAccountDtos(accountDtos);

            List<CustomerAccountsDto> customerAccountsDtos = new ArrayList<>();
            customerAccountsDtos.add(dto);
            accountPageResponse.setContent(customerAccountsDtos);
        }

        return accountPageResponse;
    }



    
    
    
    // Mapper methods
    private AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setCustomerId(account.getCustomer().getCustomerId());
        return accountDto;
    }

    private Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());
        return account;
    }
}
