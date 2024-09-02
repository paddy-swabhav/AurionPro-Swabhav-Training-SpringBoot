package com.techlab.mapipngapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.common.methods.CreateResponse;
import com.techlab.mapipngapp.dto.CreateCustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerDetailDto;

import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.entity.Account;
import com.techlab.mapipngapp.entity.Customer;

import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.operation.AccountOperation;
import com.techlab.mapipngapp.operation.CustomerOperation;
import com.techlab.mapipngapp.repository.CustomerRepository;
import com.techlab.mapipngapp.repository.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerOperation customerOperation;
	
	@Autowired
	private AccountOperation accountOperation;
	
	@Autowired
	private UserRepository userRepository;
	


	


	@Override
	public CreateCustomerAccountDto addCustomer(CreateCustomerAccountDto customerAccount) {
		// TODO Auto-generated method stub
		int customerId=customerAccount.getCustomerId();
		long accountNumber=customerAccount.getAccountNumber();
		double balance = customerAccount.getBalance();
		

		Customer dbCustomer=customerOperation.isCustomerExist(customerId);
		Account account = accountOperation.createAccount(accountNumber, balance, dbCustomer);
		
		List<Account> dbAccounts = dbCustomer.getAccounts();
		dbAccounts.add(account);
		dbCustomer.setAccounts(dbAccounts);
		
		
		dbCustomer= customerRepo.save(dbCustomer);
		
		 customerAccount.setAccountId(account.getAccountId());
		 
		 return customerAccount;
	}





	@Override
	public PageResponse<CustomerAccountDto> getAllCustomer(Integer pageNo, Integer pageSize) {
	   
	    List<CustomerAccountDto> listAccounts = customerRepo.findAll().stream()
	        .flatMap(c -> customerOperation.getAllAccountDtoOfCustomer(c).stream()) // Convert each customer to a list of CustomerAccountDto and flatten the stream
	        .collect(Collectors.toList()); // Collect to a list

	   
	    int totalElements = listAccounts.size();
	    int totalPages = (int) Math.ceil((double) totalElements / pageSize);

	   
	    int start = Math.min((pageNo - 1) * pageSize, totalElements);
	    int end = Math.min(start + pageSize, totalElements);
	    List<CustomerAccountDto> paginatedList = listAccounts.subList(start, end);

	   
	    paginatedList.forEach(a -> System.out.println(a));

	 
	    Page<CustomerAccountDto> page = new PageImpl<>(paginatedList, PageRequest.of(pageNo - 1, pageSize), totalElements);

	    
	    CreateResponse<CustomerAccountDto> createResponse = new CreateResponse<>();
	    return createResponse.createPageResponse(page);
	}





	@Override
	public PageResponse<CustomerDetailDto> getAllCustomersDetail(Integer pageNo,  Integer pageSize) {
		// TODO Auto-generated method stub
		
		   Pageable pageable = PageRequest.of(pageNo, pageSize);
		    
		    // Fetch page of Instructor entities
		    Page<Customer> page = customerRepo.findAll(pageable);
		    System.out.println(page.getContent().size()+"page");
		    // Convert Instructor entities to InstructorDto using Java streams
		    Page<CustomerDetailDto> cusPage = page.map(customer -> {
		        // Here we use a stream to process the conversion
		        return Stream.of(customer)
		                     .map(this::toCustomerDetailDtoMapper)
		                     .findFirst()
		                     .orElse(null);
		    });
		    
		   PageResponse<CustomerDetailDto> pageResponse = new PageResponse<>();
		   System.out.println(cusPage.getContent().size());//it is printing zero
		   pageResponse.setContent(cusPage.getContent());
		   pageResponse.setLastPage(cusPage.isLast());
		   pageResponse.setTotalElements(cusPage.getTotalElements());
		   pageResponse.setTotalPages(cusPage.getTotalPages());
		   pageResponse.setSize(cusPage.getSize());
		   
		    return pageResponse;
	}





	@Override
	public CustomerDetailDto getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		
	
		
		
		return toCustomerDetailDtoMapper(customerOperation.isCustomerExist(customerId));
	}





	@Override
	public PageResponse<CustomerDetailDto> getAllCustomerWithoutAccount(Integer pageNo,  Integer pageSize) {
		// TODO Auto-generated method stub
		
		
		 Pageable pageable = PageRequest.of(pageNo, pageSize);
		    
		    // Fetch page of Instructor entities
		    Page<Customer> page = customerRepo.findAll(pageable);
		    
		    // Convert Instructor entities to InstructorDto using Java streams
		    List<CustomerDetailDto> filteredList = page.stream()
		    	    .filter(customer -> customer.getAccounts() == null || customer.getAccounts().isEmpty())
		    	    .map(this::toCustomerDetailDtoMapper)
		    	    .collect(Collectors.toList());

		    	// Create a new Page with the filtered list
		    	Page<CustomerDetailDto> cusPage = new PageImpl<>(filteredList, page.getPageable(), filteredList.size());


		    
		   PageResponse<CustomerDetailDto> pageResponse = new PageResponse<>();
		   
		   pageResponse.setContent(cusPage.getContent());
		   pageResponse.setLastPage(cusPage.isLast());
		   pageResponse.setTotalElements(cusPage.getTotalElements());
		   pageResponse.setTotalPages(cusPage.getTotalPages());
		   pageResponse.setSize(cusPage.getSize());
		   
		    return pageResponse;
	}


	
	
	

	public CustomerDetailDto toCustomerDetailDtoMapper(Customer customer) {
		CustomerDetailDto customerDetailDto = new CustomerDetailDto();
		customerDetailDto.setCustomerId(customer.getCustomerId());
		Profile  profile = customer.getUser().getProfile();
		customerDetailDto.setEmail(profile.getEmailId());
		customerDetailDto.setFirstName(profile.getFirstName());
		customerDetailDto.setLastName(profile.getLastName());
		customerDetailDto.setTotalNumberOfAccounts(customer.getAccounts().size());
		//System.out.println(customerDetailDto.getTotalNumberOfAccounts());
		return customerDetailDto;
	}
	






}
