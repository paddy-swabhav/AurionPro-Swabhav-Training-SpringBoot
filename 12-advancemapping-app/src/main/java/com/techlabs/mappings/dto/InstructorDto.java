package com.techlabs.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class InstructorDto {

	private int instructorId;
	
	private String instructorName;
	
	private String email;
	
	private String qualification; 
	
}
