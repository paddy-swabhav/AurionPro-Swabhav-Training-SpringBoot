package com.techlabs.dbconnect.services;

import java.util.List;

import com.techlabs.dbconnect.entity.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(int customerid);
	
	public void addCustomer(Customer customer);
	
	public void deleteCustomerById(int customerid);
	
	public void updateCustomerById(Customer customer);
	
	public List<Customer> getCustomerByName(String name);
}
