package com.techlabs.bank.service;

import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegistrationDto;
import com.techlabs.bank.entity.User;

public interface AuthService {

	User register(RegistrationDto registration);
	String login(LoginDto loginDto);
}
