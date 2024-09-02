package com.techlabs.bank.service.impl;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = toCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return toCustomerDto(savedCustomer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.forEach(customer -> customerDtos.add(toCustomerDto(customer)));
        return customerDtos;
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            return null; // or consider throwing an exception if needed
        }
        return toCustomerDto(customerOptional.get());
    }

//    @Override
//    public CustomerDto getCustomerByEmailAndPassword(String email, String password) {
//        Customer customer = customerRepository.findByEmailAndPassword(email, password);
//        if (customer == null) {
//            return null; // or consider throwing an exception if needed
//        }
//        return toCustomerDto(customer);
//    }

    @Override
    public CustomerDto updateCustomerName(int customerId, String firstName, String lastName) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            return null; // or consider throwing an exception if needed
        }
        Customer customer = customerOptional.get();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        Customer updatedCustomer = customerRepository.save(customer);
        return toCustomerDto(updatedCustomer);
    }

    @Override
    public String updatePassword(int customerId, String oldPassword, String newPassword) {
//        Optional<Customer> customerOptional = customerRepository.findById(customerId);
//        if (customerOptional.isEmpty()) {
//            return "Customer not found";
//        }
//        Customer customer = customerOptional.get();
//        if (!customer.getPassword().equals(oldPassword)) {
//            return "Old password does not match";
//        }
//        customer.setPassword(newPassword);
//        customerRepository.save(customer);
//        return "Password updated successfully";
    	return null;
    }

    // Mapper methods
    private Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }

    private CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }
}
