package com.techlabs.mappings.Service;

import java.util.List;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Instructor;

public interface InstructorService {

	public InstructorDto addInstructor(InstructorDto instructordto);
	
	public InstructorDto getInstructorDto(int instructorId);
	
	public Instructor getInstructor(int instructorId);
	
	public List<CourseDto> getCoursesByInstructor(int instructorId);

	
	public Instructor allocateCourses(int instructorId, List<Integer> courseIds);

	public List<InstructorDto> getAllInstructors(int pagenumber, int pagesize);

	public List<StudentDto> getstudentsByInstructorId(int intstructorId);
}
