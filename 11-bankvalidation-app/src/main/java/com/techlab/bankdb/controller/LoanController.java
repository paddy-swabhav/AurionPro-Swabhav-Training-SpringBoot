package com.techlab.bankdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Loan;
import com.techlab.bankdb.entity.LoanStatus;
import com.techlab.bankdb.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public ResponseEntity<PageResponse<Loan>> getAllLoans(@Valid
                                                          @RequestParam(required = false) Integer pageNo,
                                                          @Valid
                                                          @RequestParam(required = false) Integer pageSize,
                                                          @Valid
                                                          @RequestParam(required = false) LoanStatus status) {
    	

        pageNo = (pageNo == null) ? 0 : pageNo;
        pageSize = (pageSize == null) ? 10 : pageSize;
        if(status!=null) {
        	 return ResponseEntity.ok(loanService.getLoanByStatus(status, pageNo, pageSize));
        }
        return ResponseEntity.ok(loanService.getAllLoans(pageNo, pageSize));
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<Loan> getLoanById(@Valid @PathVariable int loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    
    @PostMapping("/loans")
    public Loan addLoan(@Valid @RequestBody Loan loan) {
        return loanService.addUpdateLoan(loan);
    }

    @PutMapping("/loans")
    public Loan updateLoan(@Valid @RequestBody Loan loan) {
        return loanService.addUpdateLoan(loan);
    }

    @DeleteMapping("/loans/{loanId}")
    public String deleteLoan(@Valid @PathVariable int loanId) {
        loanService.deleteLoanById(loanId);
        return "Loan deleted successfully";
    }
}
