package com.techlabs.bank.service;

import com.techlabs.bank.dto.CustomerDto;
import java.util.List;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(int customerId);
//    CustomerDto getCustomerByEmailAndPassword(String email, String password);
    CustomerDto updateCustomerName(int customerId, String firstName, String lastName);
    String updatePassword(int customerId, String oldPassword, String newPassword);
}
