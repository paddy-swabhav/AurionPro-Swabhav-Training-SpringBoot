package com.techlab.bankdb.service;

import java.util.List;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Customer;


public interface CustomerService {
PageResponse<Customer> getAllCustomers(int pageNo,int pageSize);
	
	Customer getCustomerById(int customerId);
//	
//	List<Customer> getCustomerByName(String name);
	
	Customer addUpdateCustomer(Customer customer);
	
	void deleteCustomerById(int customerId);
	
	
	
}
