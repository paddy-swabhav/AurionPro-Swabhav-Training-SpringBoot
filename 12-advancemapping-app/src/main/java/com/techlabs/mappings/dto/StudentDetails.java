package com.techlabs.mappings.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StudentDetails {

	private int rollnumber;
	private String name;
	private int age;
}
