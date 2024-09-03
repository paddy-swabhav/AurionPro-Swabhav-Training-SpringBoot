package com.techlabs.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.service.CustomerService;

@RestController
@RequestMapping("/bank")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

	@PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto addedCustomer = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(addedCustomer);
    }

	@PreAuthorize("hasRole('ADMIN')") 
    @PutMapping("/customers")
    public ResponseEntity<CustomerDto> assignUserToCustomer(@RequestParam int userId,@RequestParam int customerId) {
    	
        return ResponseEntity.ok(customerService.assignUserToCustomer(customerId, userId));
    }
    
	@PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

	@PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") int customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        if (customerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDto);
    }

//    @GetMapping("/customers/login")
//    public ResponseEntity<CustomerDto> getCustomerByEmailAndPassword(
//            @RequestParam("email") String email,
//            @RequestParam("password") String password) {
//        CustomerDto customerDto = customerService.getCustomerByEmailAndPassword(email, password);
//        if (customerDto == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(customerDto);
//    }

	@PreAuthorize("hasRole('CUSTOMER')") 
    @PutMapping("/customers/name")
    public ResponseEntity<CustomerDto> updateCustomerName(
											            @RequestParam("customerId") int customerId,
											            @RequestParam("firstName") String firstName,
											            @RequestParam("lastName") String lastName) 
    {
        CustomerDto updatedCustomerDto = customerService.updateCustomerName(customerId, firstName, lastName);
        if (updatedCustomerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomerDto);
    }

	
	@PreAuthorize("hasRole('CUSTOMER')") 
    @PutMapping("/customers/password")
    public ResponseEntity<Customer> updatePassword(
								            @RequestParam("customerId") int customerId,
								            @RequestParam("oldPassword") String oldPassword,
								            @RequestParam("newPassword") String newPassword) 
    {
        return ResponseEntity.ok(customerService.updatePassword(customerId, oldPassword, newPassword));
    }
}
