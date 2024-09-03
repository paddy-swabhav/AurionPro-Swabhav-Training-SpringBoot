package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.UserRepository;
import com.techlabs.bank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public CustomerDto assignUserToCustomer(int customerId, int userId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        Customer customer = customerOptional.get();
        User user = userOptional.get();
        customer.setUser(user);  // Assign the User to the Customer

        Customer updatedCustomer = customerRepository.save(customer);  // Save the updated Customer entity
        return toCustomerDto(updatedCustomer);
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
    public Customer updatePassword(int customerId, String oldPassword, String newPassword) {
    	 // Retrieve the customer by ID
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new NullPointerException("Customer not found with ID: " + customerId);
        }

        Customer customer = customerOptional.get();
        User user = customer.getUser();

        // Check if the old password matches
        if (!user.getPassword().equals(oldPassword)) {
            throw new NullPointerException("Old password is incorrect.");
        }

        // Set the new password
        user.setPassword(newPassword);
        
        // Save the updated User
        userRepository.save(user);

        // Save the updated Customer
        return customerRepository.save(customer);
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
