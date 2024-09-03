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

import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.service.TransactionService;

@RestController
@RequestMapping("/bank")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

	@PreAuthorize("hasRole('CUSTOMER')") 
    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> performTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto performedTransaction = transactionService.performTransaction(transactionDto);
        return ResponseEntity.ok(performedTransaction);
    }
    
	@PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/transactions")
    public ResponseEntity<PageResponse<TransactionDto>> getAllTransactions(@RequestParam(defaultValue = "0") int pagenumber, @RequestParam(defaultValue = "10") int pagesize) {
        
        return ResponseEntity.ok(transactionService.getAllTransactions(pagenumber,pagesize));
    }

	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')") 
    @GetMapping("/transactions/account")
    public ResponseEntity<PageResponse<TransactionDto>> getTransactionsForAccount(@RequestParam(defaultValue = "0") int pagenumber, @RequestParam(defaultValue = "10") int pagesize, @RequestParam long accountNumber) {

        return ResponseEntity.ok(transactionService.getTransactionsForAccount(pagenumber,pagesize, accountNumber));
    }
}
