package com.techlab.mapipngapp.service;

import com.techlab.mapipngapp.dto.CreateCustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerDetailDto;
import com.techlab.mapipngapp.dto.PageResponse;


public interface CustomerService {
	
	CreateCustomerAccountDto addCustomer(CreateCustomerAccountDto customerAccount );
	

	
	PageResponse<CustomerAccountDto> getAllCustomer( Integer pageNo,  Integer pageSize);
	
	PageResponse<CustomerDetailDto>  getAllCustomersDetail(Integer pageNo,  Integer pageSize);
	
	CustomerDetailDto getCustomerById(int customerId);
	
	PageResponse<CustomerDetailDto>  getAllCustomerWithoutAccount(Integer pageNo,  Integer pageSize);
	
	

}
