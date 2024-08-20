package com.techlabs.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Student;
import com.techlabs.dbconnect.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentrepo;
	
	@Override
	public List<Student> getAllStudents() {

		return studentrepo.getAllStudents();
	}

	@Override
	public Student getStudent(int rollnumber) {
		
		return studentrepo.getStudent(rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		
		studentrepo.addStudent(student);
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		return studentrepo.getStudentsByName(name);
	}

	@Override
	public void deleteStudent(int rollnumber) {
		
		studentrepo.deleteStudent(rollnumber);;
	}

}
