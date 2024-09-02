package com.techlabs.bank.controller;

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

import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.service.TransactionService;

@RestController
@RequestMapping("/bank")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> performTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto performedTransaction = transactionService.performTransaction(transactionDto);
        return ResponseEntity.ok(performedTransaction);
    }
    
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transactions/account")
    public ResponseEntity<List<TransactionDto>> getTransactionsForAccount(@RequestParam long accountNumber) {
        List<TransactionDto> transactions = transactionService.getTransactionsForAccount(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}
