package com.techlabs.jwtsecured.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.jwtsecured.dto.LoginDto;
import com.techlabs.jwtsecured.dto.RegistrationDto;
import com.techlabs.jwtsecured.entity.Role;
import com.techlabs.jwtsecured.entity.User;
import com.techlabs.jwtsecured.exception.UserApiException;
import com.techlabs.jwtsecured.repo.RoleRepository;
import com.techlabs.jwtsecured.repo.UserRepository;
import com.techlabs.jwtsecured.security.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	public User register(RegistrationDto registration) {
		
		if(userRepo.existsByUsername(registration.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User already exists");
		
		User user = new User();
		user.setUsername(registration.getUsername());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		
		Role userRole = roleRepo.findByRolename(registration.getRole()).get();
		roles.add(userRole);
		user.setRoles(roles);
		
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			
			return token;
		}
		catch(BadCredentialsException e)
		{
			throw new UserApiException(HttpStatus.NOT_FOUND, "username");
		}

	}
	
	
	
}
