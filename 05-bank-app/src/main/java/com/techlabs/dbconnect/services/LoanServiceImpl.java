package com.techlabs.dbconnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Loan;
import com.techlabs.dbconnect.entity.LoanStatus;
import com.techlabs.dbconnect.repositorys.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	private LoanRepository loanRepo;
	
	@Override
	public List<Loan> getAllLoans() {
		
		return loanRepo.getAllLoans();
	}

	@Override
	public Loan getLoanById(int loanid) {
		
		return loanRepo.getLoanById(loanid);
	}

	@Override
	public void addLoan(Loan loan) {
		
		loanRepo.addLoan(loan);
	}

	@Override
	public void deleteLoanById(int loanid) {
		
		loanRepo.deleteLoanById(loanid);
	}

	@Override
	public void updateLoanById(Loan loan) {
		
		loanRepo.updateLoanById(loan);
	}

	@Override
	public List<Loan> getLoanByStatus(LoanStatus loanstatus) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
