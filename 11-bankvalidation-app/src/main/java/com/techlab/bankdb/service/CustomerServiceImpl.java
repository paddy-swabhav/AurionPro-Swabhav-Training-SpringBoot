package com.techlab.bankdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Customer;
import com.techlab.bankdb.exceptions.CustomerNotFoundException;
import com.techlab.bankdb.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	PageResponse<Customer> customerPageResponse = new PageResponse<>();

	@Override
	public PageResponse<Customer> getAllCustomers(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(pageNo, pageSize); 
		Page <Customer> customerPage= customerRepository.findAll(page);
		customerPageResponse.setContent(customerPage.getContent());
		customerPageResponse.setSize(customerPage.getSize());
		customerPageResponse.setTotalElements(customerPage.getTotalElements());
		customerPageResponse.setTotalPages(customerPage.getTotalPages());
		customerPageResponse.setLastPage(customerPage.isLast());
		
		return customerPageResponse;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> dbCustomer = customerRepository.findById(customerId);
		if(!dbCustomer.isPresent()) {
			throw new CustomerNotFoundException();
		}
		return dbCustomer.get();
	}

	@Override
	public Customer addUpdateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(customer.getCustomerId()!=0) {
			
			Optional<Customer> dbCustomer = customerRepository.findById(customer.getCustomerId());
			if(!dbCustomer.isPresent()) {
				throw new CustomerNotFoundException();
			}
			
		}
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(int customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> dbCustomer = customerRepository.findById(customerId);
		if(!dbCustomer.isPresent()) {
			throw new CustomerNotFoundException();
		}
		customerRepository.deleteById(customerId);
		
	}



	

}
