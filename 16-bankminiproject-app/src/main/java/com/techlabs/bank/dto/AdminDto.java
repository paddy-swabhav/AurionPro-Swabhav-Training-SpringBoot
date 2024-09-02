package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdminDto {
	
	private int adminId;
    private String email;
    private String password;
    private String name;
}