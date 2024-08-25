package com.techlabs.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

	
	private int courseId;
	
	private String courseName;
	
	private int duration;
	
	private double fees;
}
