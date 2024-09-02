package com.techlab.mapipngapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.CustomerUserDto;
import com.techlab.mapipngapp.dto.LoginDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.dto.UserDto;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//creating customer
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody @Valid  User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@GetMapping("/users")
	public ResponseEntity<PageResponse<User>> getAllUsers(@Valid
            @RequestParam(required = false) Integer pageNo,
            @Valid
            @RequestParam(required = false) Integer pageSize
                                      ){
			  
     pageNo = pageNo == null ? 0 : pageNo; 
     pageSize = pageSize == null ? 10 : pageSize;
     return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize));
     }

	@PostMapping("/users/customer")
	public ResponseEntity<User> updateUserByCustomerId(@RequestParam int customerId ,  @RequestBody @Valid CustomerUserDto user) {
		return ResponseEntity.ok(userService.updateCustomerUser(customerId,user));
	}
	
	@GetMapping("/users/customer")
	public ResponseEntity<UserDto> getUserByCustomer(@RequestParam int customerId) {
		return ResponseEntity.ok(userService.getUserByCustomerId(customerId));
	}
	
	
	@PostMapping("/user/login")
	public ResponseEntity<UserDto> loginUser(@Valid @RequestBody LoginDto login){
		return ResponseEntity.ok(userService.userLogin(login.getEmail(), login.getPassword()));
	}

}
