package com.techlabs.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Student;
import com.techlabs.dbconnect.service.StudentService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/StudentApp")
public class StudentController {

	@Autowired
	private StudentService studentservice;

	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		return ResponseEntity.ok(studentservice.getAllStudents());
	}
	
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber)
	{
		return ResponseEntity.ok(studentservice.getStudent(rollnumber));
	}
	
	@PostMapping("/students")
	@Transactional
	public String addStudent(@RequestBody Student student)
	{
		studentservice.addStudent(student);
		return "Record Added Successfully";
	}
	
	
	@GetMapping("/students/name")
	public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name)
	{
	
		return ResponseEntity.ok(studentservice.getStudentsByName(name));
	}
		
	@DeleteMapping("/students/{rollnumber}")
	@Transactional
	public String deleteStudent(@PathVariable int rollnumber) {
		
		studentservice.deleteStudent(rollnumber);
		
		return "Record Deleted Successfully";
	}
	
}
