package com.techlab.bankdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.bankdb.entity.Loan;
import com.techlab.bankdb.entity.LoanStatus;

public interface LoanRepository extends  JpaRepository<Loan, Integer>{

	Page<Loan> findByLoanStatus(LoanStatus status, Pageable page);

}
