package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AccountDto;
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
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
