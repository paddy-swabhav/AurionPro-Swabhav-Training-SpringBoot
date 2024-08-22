package com.techlabs.jpacrud.controller;

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

import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired
	private StudentService studentservice;
	
	@GetMapping("/students")
	public ResponseEntity<PageResponse<Student>> getAllStudents(@RequestParam(required = false) String name, @RequestParam(required = false) int pagenumber, @RequestParam(required = false) int pagesize)
	{
		if(name!=null)
			return ResponseEntity.ok(studentservice.getAllStudentsByname(name,pagenumber,pagesize));
		return ResponseEntity.ok(studentservice.getAllStudents(pagenumber,pagesize));
	}

	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Student> getStudentByRollnumber(@PathVariable int rollnumber) {
		
		return ResponseEntity.ok(studentservice.getStudentByRollnumber(rollnumber));
	}

	@PostMapping("/students")
	public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
		
		return ResponseEntity.ok(studentservice.saveStudent(student));
	}

	@DeleteMapping("/students")
	public String deleteStudent( @RequestParam int rollnumber) {
		
		studentservice.deleteStudent(rollnumber);
		return "Student Deleted";
	}
	
	
	
//	@GetMapping("/students")
//	public ResponseEntity<List<Student>> getAllStudentsByName(@RequestParam String name)
//	{
//		if(name!=null)
//			return ResponseEntity.ok(studentservice.getAllStudentsByname(name));
//		return ResponseEntity.ok(studentservice.getAllStudents());
//	}
}
