package com.techlabs.bank.controller;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto addedCustomer = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(addedCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") int customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        if (customerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/customers/login")
    public ResponseEntity<CustomerDto> getCustomerByEmailAndPassword(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        CustomerDto customerDto = customerService.getCustomerByEmailAndPassword(email, password);
        if (customerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDto);
    }

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

    @PutMapping("/customers/password")
    public ResponseEntity<String> updatePassword(
								            @RequestParam("customerId") int customerId,
								            @RequestParam("oldPassword") String oldPassword,
								            @RequestParam("newPassword") String newPassword) 
    {
        String result = customerService.updatePassword(customerId, oldPassword, newPassword);
        return ResponseEntity.ok(result);
    }
}
