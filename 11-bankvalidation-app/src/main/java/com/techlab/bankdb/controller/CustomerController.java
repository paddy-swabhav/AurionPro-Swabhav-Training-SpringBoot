package com.techlab.bankdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Customer;
import com.techlab.bankdb.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	public ResponseEntity<PageResponse<Customer>> getAllCustomers(@Valid
			                                                      @RequestParam(required = false) Integer pageNo,
			                                                      @Valid
			                                                      @RequestParam(required = false) Integer pageSize
			){
																  
		pageNo = pageNo == null ? 0 : pageNo; 
		pageSize = pageSize == null ? 10 : pageSize;
		return ResponseEntity.ok(customerService.getAllCustomers(pageNo, pageSize));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@Valid @PathVariable int customerId){
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	
	@PostMapping("/customer")
	public Customer addCustomer(@Valid @RequestBody Customer customer){
		return customerService.addUpdateCustomer(customer);
		
	}
	
	
	@DeleteMapping("/customer/{customerId}")
	public String deleteCustomer(@Valid @PathVariable int customerId) {
		customerService.deleteCustomerById(customerId);
		return "customer deleted successfully";
	}
}
