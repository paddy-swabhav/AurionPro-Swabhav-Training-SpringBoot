package com.techlabs.bank.config;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techlabs.bank.security.JwtAuthenticationEntryPoint;
import com.techlabs.bank.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

@EnableMethodSecurity
@Configuration
@Data
@AllArgsConstructor
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	static PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	    return configuration.getAuthenticationManager();
	  }
	
//	private static final String[] PUBLIC_URLS = {"/insuranceapp/login"}
	
	@Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	    http.csrf(csrf -> csrf.disable()).cors(withDefaults());
	    http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
	    
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bank/users/register").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bank/users/login").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bank/users/captcha").permitAll());

	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/bank/"));
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/bank/"));
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.PUT, "/bank/"));
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/bank/"));
	    http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
	    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
	    return http.build();
	  }
}
