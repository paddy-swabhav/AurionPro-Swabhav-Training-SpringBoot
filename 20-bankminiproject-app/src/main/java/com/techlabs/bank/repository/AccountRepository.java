package com.techlabs.bank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.bank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Page<Account> findByCustomer_CustomerId(int customerId, Pageable pageable);
	
}
