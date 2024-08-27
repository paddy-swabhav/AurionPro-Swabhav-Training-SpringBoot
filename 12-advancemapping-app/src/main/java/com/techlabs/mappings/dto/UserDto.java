package com.techlabs.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	private int userId;
	
	private String userName;
	
	private String password;
}
