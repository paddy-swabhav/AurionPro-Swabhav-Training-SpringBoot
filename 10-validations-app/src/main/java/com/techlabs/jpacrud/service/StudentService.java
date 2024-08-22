package com.techlabs.jpacrud.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;

public interface StudentService {

	public PageResponse<Student> getAllStudents(int pagenumber,int pagesize);
	
	public Student getStudentByRollnumber(int rollnumber);
	
	public Student saveStudent(Student student);
	
	public void deleteStudent(int rollnumber);
	
	public PageResponse<Student> getAllStudentsByname(String name,int pagenumber,int pagesize);
}
