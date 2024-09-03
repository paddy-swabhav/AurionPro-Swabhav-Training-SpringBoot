package com.techlabs.bank.service;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.entity.Customer;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    
    PageResponse<CustomerDto> getAllCustomers(int pagenumber, int pagesize);
    
    CustomerDto getCustomerById(int customerId);
    
//    CustomerDto getCustomerByEmailAndPassword(String email, String password);
    
    CustomerDto updateCustomerName(int customerId, String firstName, String lastName);
    
    Customer updatePassword(int customerId, String oldPassword, String newPassword);
    
	CustomerDto assignUserToCustomer(int customerId, int userId);
}
