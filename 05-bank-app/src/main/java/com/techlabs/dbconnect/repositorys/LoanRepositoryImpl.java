package com.techlabs.dbconnect.repositorys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Loan;
import com.techlabs.dbconnect.entity.LoanStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class LoanRepositoryImpl implements LoanRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Loan> getAllLoans() {
		
		TypedQuery<Loan> query = manager.createQuery("SELECT c FROM Loan c",Loan.class);
		return query.getResultList();
	}

	@Override
	public Loan getLoanById(int loanid) {
		
		return manager.find(Loan.class, loanid);
	}

	@Override
	public void addLoan(Loan loan) {
		
		manager.persist(loan);
	}

	@Override
	public void deleteLoanById(int loanid) {
		
		 Query query = manager.createQuery("DELETE from Loan c where c.loanid=:theloanid");
		 query.setParameter("theloanid", loanid);
		 query.executeUpdate();
	}

	@Override
	public void updateLoanById(Loan loan) {
		
		Loan existingLoan = manager.find(Loan.class, loan.getLoanid());
	    if (existingLoan != null) {
	        manager.merge(loan);
	    } else {
	        System.out.println("Loan not found for update");
	    }
		
	}

	@Override
	public List<Loan> getLoanByStatus(LoanStatus loanstatus) {
		// TODO Auto-generated method stub
		return null;
	}	



}
