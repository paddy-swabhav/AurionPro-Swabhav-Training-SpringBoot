package com.techlabs.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Loan;
import com.techlabs.dbconnect.entity.LoanStatus;
import com.techlabs.dbconnect.services.LoanService;

import jakarta.transaction.Transactional;

@RequestMapping("/bankapp")
@RestController
public class LoanController {
	
	@Autowired
	private LoanService loanservice;
	
	@GetMapping("/loan")
	public ResponseEntity<List<Loan>> getAllLoans() {
		
		return ResponseEntity.ok(loanservice.getAllLoans());
	}

	@GetMapping("/loan/{loanid}")
	public ResponseEntity<Loan> getLoanById(@PathVariable int loanid) {
		
		return ResponseEntity.ok(loanservice.getLoanById(loanid));
	}

	@PostMapping("/loan")
	@Transactional
	public String addLoan(@RequestBody Loan loan) {
		
		loanservice.addLoan(loan);
		return "Loan added";
	}

	@DeleteMapping("/loan/{loanid}")
	@Transactional
	public String deleteLoanById(@PathVariable int loanid) {
		
		loanservice.deleteLoanById(loanid);
		return "Loan Deleted";
	}

	@PutMapping("/loan")
	@Transactional
	public String updateLoanById(@RequestBody Loan loan) {
	
		loanservice.updateLoanById(loan);
		return "Loan Updated";
	}
	
	@GetMapping("/loan/status/{loanstatus}")
	public ResponseEntity<List<Loan>> getLoanByStatus(@PathVariable LoanStatus loanstatus)
	{
		return ResponseEntity.ok(loanservice.getLoanByStatus(loanstatus));
	}
	

	
}