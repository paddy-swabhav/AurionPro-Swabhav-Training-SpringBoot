package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.PageResponse;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.UserRepository;
import com.techlabs.bank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = toCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return toCustomerDto(savedCustomer);
    }

    @Override
    public PageResponse<CustomerDto> getAllCustomers(int pageNo, int pageSize) {

    	Pageable page = PageRequest.of(pageNo, pageSize); 
		Page<Customer> customerPage = customerRepository.findAll(page);
       
		PageResponse<CustomerDto> customerPageResponse = new PageResponse();
		customerPageResponse.setTotalPages(customerPage.getTotalPages());
		customerPageResponse.setTotalElements(customerPage.getTotalElements());
		customerPageResponse.setSize(customerPage.getSize());
		customerPageResponse.setLastPage(customerPage.isLast());
		
		List<CustomerDto> customerDtos = new ArrayList<>();
		
		customerPage.getContent().forEach((customer)->{
			customerDtos.add(toCustomerDto(customer));
		});
        
		customerPageResponse.setContent(customerDtos);

        return customerPageResponse;
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        return toCustomerDto(customerOptional.get());
    }

    @Override
    public CustomerDto assignUserToCustomer(int customerId, int userId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
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
            throw new CustomerNotFoundException(customerId);
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
            throw new CustomerNotFoundException(customerId);
        }

        Customer customer = customerOptional.get();
        User user = customer.getUser();

        // Check if the old password matches the encoded password in the database
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect.");
        }

        // Set the new password (encode it before saving)
        user.setPassword(passwordEncoder.encode(newPassword));
        
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
