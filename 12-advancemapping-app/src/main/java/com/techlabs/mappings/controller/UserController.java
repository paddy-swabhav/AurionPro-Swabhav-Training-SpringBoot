package com.techlabs.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.Service.UserService;
import com.techlabs.mappings.dto.UserDto;
import com.techlabs.mappings.entity.User;

@RestController
@RequestMapping("/studentapp")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto)
	{
		return ResponseEntity.ok(userService.addUser(userDto));
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> assignRoleToUser(@RequestParam int userId, @RequestBody List<Integer> roleId)
	{
		return ResponseEntity.ok(userService.assignRoleToUser(userId, roleId));
	}
}
