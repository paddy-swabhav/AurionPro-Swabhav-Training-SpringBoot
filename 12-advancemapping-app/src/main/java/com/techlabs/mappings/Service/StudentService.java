package com.techlabs.mappings.Service;

import java.util.List;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Student;

public interface StudentService {

	public StudentDto addStudent(Student student);
	
	public PageResponse<StudentDto> getAllStudents(int pagenumber,int pagesize);
	
	public StudentDto findStudentDetailsById(int rollnumber);
	
	public Address getStudentAddressById(int rollnumber);

	public Student updateStudentCityById(int rollnumber, Address city);

	public StudentDto assignCourses(int rollnumber, List<Integer> courseIds);
}
