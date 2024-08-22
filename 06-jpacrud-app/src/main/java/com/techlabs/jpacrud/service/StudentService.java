package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.jpacrud.entity.Student;

public interface StudentService {

	public List<Student> getAllStudents();
	
	public Optional<Student> getStudentByRollnumber(int rollnumber);
	
	public void saveStudent(Student student);
	
	public void deleteStudent(int rollnumber);
	
	public List<Student> getAllStudentsByname(String name);
}
