package com.techlab.mapipngapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.entity.Customer;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account,Integer>{
	Account findByAccountNumber(Long accountNumber);
	
	List<Account> findByCustomer(Customer customer);
	
	
}
