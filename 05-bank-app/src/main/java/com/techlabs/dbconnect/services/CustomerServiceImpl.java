package com.techlabs.dbconnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Customer;
import com.techlabs.dbconnect.repositorys.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepo.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(int customerid) {
		
		return customerRepo.getCustomerById(customerid);
	}

	@Override
	public void addCustomer(Customer customer) {
		
		customerRepo.addCustomer(customer);
	}

	@Override
	public void deleteCustomerById(int customerid) {
		
		customerRepo.deleteCustomerById(customerid);
	}

	@Override
	public void updateCustomerById(Customer customer) {
		
		customerRepo.updateCustomerById(customer);
	}

	@Override
	public List<Customer> getCustomerByName(String name) {
		
		return customerRepo.getCustomerByName(name);
	}

	
}
