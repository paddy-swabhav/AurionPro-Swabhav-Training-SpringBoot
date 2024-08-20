package com.techlabs.dbconnect.controllers;

import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Customer;
import com.techlabs.dbconnect.services.CustomerService;

import jakarta.persistence.PostUpdate;
import jakarta.transaction.Transactional;

@RequestMapping("/bankapp")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		
		return customerservice.getAllCustomers();
	}

	@GetMapping("/customer/{customerid}")
	public Customer getCustomerById(@PathVariable int customerid) {
		
		return customerservice.getCustomerById(customerid);
	}

	@PostMapping("/customer")
	@Transactional
	public String addCustomer(@RequestBody Customer customer) {
		
		customerservice.addCustomer(customer);
		return "Customer added";
	}

	@DeleteMapping("/customer/{customerid}")
	@Transactional
	public String deleteCustomerById(@PathVariable int customerid) {
		
		customerservice.deleteCustomerById(customerid);
		return "Customer Deleted";
	}

	@PutMapping("/customer")
	@Transactional
	public String updateCustomerById(@RequestBody Customer customer) {
	
		customerservice.updateCustomerById(customer);
		return "Customer Updated";
	}
	
	

	
}
