package com.techlabs.bank.service;

import java.util.List;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.entity.Customer;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    
    List<CustomerDto> getAllCustomers();
    
    CustomerDto getCustomerById(int customerId);
    
//    CustomerDto getCustomerByEmailAndPassword(String email, String password);
    
    CustomerDto updateCustomerName(int customerId, String firstName, String lastName);
    
    Customer updatePassword(int customerId, String oldPassword, String newPassword);
    
	CustomerDto assignUserToCustomer(int customerId, int userId);
}
