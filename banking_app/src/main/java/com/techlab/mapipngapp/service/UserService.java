package com.techlab.mapipngapp.service;


import com.techlab.mapipngapp.dto.CustomerUserDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.User;

import jakarta.validation.Valid;

public interface UserService {
	
	
	User createUser(User user);

	PageResponse<User> getAllUsers(@Valid Integer pageNo, @Valid Integer pageSize);
	
	
	User updateCustomerUser(int customerId , CustomerUserDto customerDto);
	
	UserDto getUserByCustomerId(int customerId);
	
	UserDto userLogin(String email,String password);

}
