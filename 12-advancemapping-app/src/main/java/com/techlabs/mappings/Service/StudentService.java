package com.techlabs.mappings.Service;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDetails;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Student;

public interface StudentService {

	public Student addStudent(Student student);
	
	public PageResponse<Student> getAllStudents(int pagenumber,int pagesize);
	
	public StudentDetails findStudentDetailsById(int rollnumber);
	
	public Address getStudentAddressById(int rollnumber);

	public Student updateStudentCityById(int rollnumber, Address city);
}
