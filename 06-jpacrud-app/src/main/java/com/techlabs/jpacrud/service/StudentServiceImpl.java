package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepo.findAll();
	}

	@Override
	public Optional<Student> getStudentByRollnumber(int rollnumber) {
		
		return studentRepo.findById(rollnumber);
	}

	@Override
	public void saveStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void deleteStudent(int rollnumber) {
		
		studentRepo.deleteById(rollnumber);
	}

	@Override
	public List<Student> getAllStudentsByname(String name) {

		return studentRepo.findByName(name);
	}

	
}
