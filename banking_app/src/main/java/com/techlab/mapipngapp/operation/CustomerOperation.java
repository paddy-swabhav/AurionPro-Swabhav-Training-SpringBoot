package com.techlab.mapipngapp.operation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techlab.mapipngapp.dto.CustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerDetailDto;
import com.techlab.mapipngapp.entity.Customer;
import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.repository.CustomerRepository;

@Component
public class CustomerOperation {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private Customer customer;
	
	public Customer createCustomer(User user) {
		customer.setUser(user);
//		System.out.println(customer.getCustomerId());
		customer.setCustomerId(0);
		return customerRepository.save(customer);
	}
	
	public Customer isCustomerExist(int customerId) {
		Optional<Customer> dbCustomer = customerRepository.findById(customerId);
		
		if(!dbCustomer.isPresent()) {
			throw new RuntimeException("Customer Not present with Id: "+customerId);
		}
		
		return dbCustomer.get();
	}
	
	public List<CustomerAccountDto> getAllAccountDtoOfCustomer(Customer customer) {
	    Profile profile = customer.getUser().getProfile();
	    String firstName = profile.getFirstName();
	    String lastName = profile.getLastName();

	    List<CustomerAccountDto> customerAccountDtos = customer.getAccounts().stream()
	        .map(a -> {
	            CustomerAccountDto customerAccountDto = new CustomerAccountDto();
	            customerAccountDto.setFirstName(firstName);
	            customerAccountDto.setLastName(lastName);
	            customerAccountDto.setAccountNumber(a.getAccountNumber());
	            customerAccountDto.setBalance(a.getBalance());
	            customerAccountDto.setCustomerId(customer.getCustomerId());
	            return customerAccountDto;
	        })
	        .collect(Collectors.toList()); // Collect the results into a List

	    return customerAccountDtos;
	}
	
	
	public CustomerDetailDto toCustomerDetailDtoMapper(Customer customer) {
		CustomerDetailDto customerDetailDto = new CustomerDetailDto();
		customerDetailDto.setCustomerId(customer.getCustomerId());
		Profile  profile = customer.getUser().getProfile();
		customerDetailDto.setEmail(profile.getEmailId());
		customerDetailDto.setFirstName(profile.getFirstName());
		customerDetailDto.setLastName(profile.getLastName());
		customerDetailDto.setTotalNumberOfAccounts(customer.getAccounts().size());
		return customerDetailDto;
	}


}
