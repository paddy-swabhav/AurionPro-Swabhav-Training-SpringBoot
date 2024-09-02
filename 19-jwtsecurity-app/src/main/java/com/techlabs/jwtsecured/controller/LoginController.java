package com.techlabs.jwtsecured.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techlabs.jwtsecured.dto.JwtAuthResponse;
import com.techlabs.jwtsecured.dto.LoginDto;
import com.techlabs.jwtsecured.dto.RegistrationDto;
import com.techlabs.jwtsecured.entity.User;
import com.techlabs.jwtsecured.service.AuthService;

@Controller
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto)
	{
		return ResponseEntity.ok(authService.register(registrationDto));
	}
	
	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto)
	{
		String token = authService.login(loginDto);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
}
