package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.entity.Bank;
import com.techlabs.cashmgmt.service.BankService;

@RestController
@RequestMapping("/cashapp")
public class BankController {

	@Autowired
	public BankService bankService;
	
	@PostMapping("/bank")
	public ResponseEntity<Bank> addBankDetails(@RequestBody Bank bank)
	{
		return ResponseEntity.ok(bankService.addBank(bank));
	}
	
	@GetMapping("/bank")
	public ResponseEntity<Page<Bank>> getAllBank(@RequestParam(defaultValue = "0") int pagenumber,@RequestParam(defaultValue = "10") int pagesize)
	{
		return ResponseEntity.ok(bankService.getAllBanks(pagenumber,pagesize));
	}
}
