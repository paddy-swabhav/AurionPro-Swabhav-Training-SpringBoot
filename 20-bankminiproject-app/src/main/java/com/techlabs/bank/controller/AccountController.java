package com.techlabs.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.CustomerAccountsDto;
import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.service.AccountService;

@RestController
@RequestMapping("/bank")
public class AccountController {

    @Autowired
    private AccountService accountService;

	@PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return ResponseEntity.ok(createdAccount);
    }
    
	@PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/accounts")
    public ResponseEntity<PageResponse<AccountDto>> getAllAccounts(@RequestParam(defaultValue = "0") int pagenumber,@RequestParam(defaultValue = "10") int pagesize) {
        return ResponseEntity.ok(accountService.getAllAccounts(pagenumber,pagesize));
    }
	
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	@GetMapping("/customer/accounts")
	public ResponseEntity<PageResponse<CustomerAccountsDto>> getAccountsForCustomer(@RequestParam int customerId,
	                                                                                    @RequestParam(defaultValue = "0") int pageNo,
	                                                                                    @RequestParam(defaultValue = "10") int pageSize) {
	PageResponse<CustomerAccountsDto> accountPageResponse = accountService.getAccountsForCustomer(pageNo, pageSize, customerId);
	     return ResponseEntity.ok(accountPageResponse);
	}
	
}
