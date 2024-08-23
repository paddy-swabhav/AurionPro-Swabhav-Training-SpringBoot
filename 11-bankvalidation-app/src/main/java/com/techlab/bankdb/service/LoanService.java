package com.techlab.bankdb.service;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Loan;
import com.techlab.bankdb.entity.LoanStatus;


public interface LoanService {
	PageResponse<Loan> getAllLoans(int pageNo,int pageSize);
	
	Loan getLoanById(int LoanId);
//	
	PageResponse<Loan> getLoanByStatus(LoanStatus status,int pageNo,int pageSize);
	
	Loan addUpdateLoan(Loan Loan);
	
	void deleteLoanById(int LoanId);
	
	
}
