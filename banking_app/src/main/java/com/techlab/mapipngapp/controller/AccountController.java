package com.techlab.mapipngapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.AccountDto;
import com.techlab.mapipngapp.dto.PerformTransactionDto;
import com.techlab.mapipngapp.dto.TransactionResponseDto;
import com.techlab.mapipngapp.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts/accountNumber")
	public ResponseEntity<Long> generateAccountNumber(){
		return ResponseEntity.ok(accountService.genereateAccountNumber());
	   	
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<AccountDto> getAccountByAccountNumber(@RequestParam long accountNumber){
		return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));
	   	
	}
	
	@GetMapping("/accounts/customer/{customerId}")
	public ResponseEntity<List<AccountDto>> getAllAccountByCustomerId(@PathVariable int customerId){
		return ResponseEntity.ok(accountService.getAllAccountByCustomerId(customerId));
	   	
	}
	
	@PostMapping("/accounts/transaction")
	public ResponseEntity<TransactionResponseDto> performTransaction(@RequestBody @Valid PerformTransactionDto transactionDto){
		
		return ResponseEntity.ok(accountService.performTransaction(transactionDto));
	}
}
