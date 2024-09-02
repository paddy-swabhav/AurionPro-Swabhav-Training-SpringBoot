package com.techlab.mapipngapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.AdminTransactionDto;
import com.techlab.mapipngapp.dto.CustomerTransactionDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.service.TransactionService;

@RestController
@RequestMapping("/bankapp")
public class TransationController {
	
	@Autowired
	private TransactionService transactionService;
	@GetMapping("/admin/transaction")
	public ResponseEntity<PageResponse<AdminTransactionDto>> getAllTransactions(@RequestParam Integer pageNo,@RequestParam Integer pageSize ){
		return ResponseEntity.ok(transactionService.getAllTransactions(pageNo, pageSize));
	}
	
	
	@GetMapping("/customer/transaction")
	public ResponseEntity<PageResponse<CustomerTransactionDto>> getAllAccountTransactions(@RequestParam Integer pageNo,@RequestParam Integer pageSize ,@RequestBody Long accountNumber){
		return ResponseEntity.ok(transactionService.getTransactionByAccount(accountNumber,pageNo, pageSize));
	}

}
