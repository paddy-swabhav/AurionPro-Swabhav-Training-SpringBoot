package com.techlabs.jwtsecured.service;

import java.util.List;

import com.techlabs.jwtsecured.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);
}
