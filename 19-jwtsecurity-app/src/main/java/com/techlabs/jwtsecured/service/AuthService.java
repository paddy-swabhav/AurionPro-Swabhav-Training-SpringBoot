package com.techlabs.jwtsecured.service;

import com.techlabs.jwtsecured.dto.LoginDto;
import com.techlabs.jwtsecured.dto.RegistrationDto;
import com.techlabs.jwtsecured.entity.User;

public interface AuthService {

	User register(RegistrationDto registration);
	String login(LoginDto loginDto);
}
