package com.techlabs.jwtsecured.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.jwtsecured.entity.Customer;
import com.techlabs.jwtsecured.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return customerRepo.findById(customerId).orElseThrow(()-> new NullPointerException("No Customer with specified Id found"));
	}

}
