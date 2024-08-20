package com.techlabs.dbconnect.services;

import java.util.List;

import com.techlabs.dbconnect.entity.Loan;
import com.techlabs.dbconnect.entity.LoanStatus;

public interface LoanService {

public List<Loan> getAllLoans();
	
	public Loan getLoanById(int loanid);
	
	public void addLoan(Loan loan);
	
	public void deleteLoanById(int loanid);
	
	public void updateLoanById(Loan loan);
	
	public List<Loan> getLoanByStatus(LoanStatus loanstatus);
}
