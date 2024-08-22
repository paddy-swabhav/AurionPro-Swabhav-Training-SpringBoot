package com.techlabs.jpacrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.exception.StudentNotFoundException;
import com.techlabs.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public PageResponse<Student> getAllStudents(int pagenumber,int pagesize) {
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<Student> studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		
		return studentPageResponse;
	}

	@Override
	public Student getStudentByRollnumber(int rollnumber) {
		
		Optional<Student> dbstudent = studentRepo.findById(rollnumber);
		if(!dbstudent.isPresent())
			throw new StudentNotFoundException();
		
		return dbstudent.get();
	}

	@Override
	public Student saveStudent(Student student) {
		
		return studentRepo.save(student);
	}

	@Override
	public void deleteStudent(int rollnumber) {
		
		studentRepo.deleteById(rollnumber);
	}

	@Override
	public PageResponse<Student> getAllStudentsByname(String name,int pagenumber,int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Student> studentPage = studentRepo.findByName(name, pageable);
		
		PageResponse<Student> studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		
		return studentPageResponse;
	}

	
}
