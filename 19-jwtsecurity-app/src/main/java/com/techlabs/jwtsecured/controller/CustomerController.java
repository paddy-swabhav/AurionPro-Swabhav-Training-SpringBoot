package com.techlabs.jwtsecured.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techlabs.jwtsecured.entity.Customer;
import com.techlabs.jwtsecured.service.CustomerService;

@Controller
@RequestMapping("/app")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")  // hasRole for only single role
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@GetMapping("/customers/{customerId}")
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")  // hasAnyRole for multiple roles
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerId)
	{
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
}
