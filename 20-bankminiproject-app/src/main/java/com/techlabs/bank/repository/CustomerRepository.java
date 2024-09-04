package com.techlabs.bank.repository;

import com.techlabs.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    Customer findByEmailAndPassword(String email, String password);
	Customer findByAccounts_AccountNumber(long accountnumber);
	Customer findByUser_Username(String username);
}
