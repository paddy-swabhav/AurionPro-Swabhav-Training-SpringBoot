package com.techlabs.dbconnect.repository;

import java.util.List;

import com.techlabs.dbconnect.entity.Student;

public interface StudentRepository {

	public List<Student> getAllStudents();
	
	public Student getStudent(int rollnumber);
	
	public void addStudent(Student student);
	
	public List<Student> getStudentsByName(String name);
	
	public void deleteStudent(int rollnumber);
}
