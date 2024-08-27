package com.techlabs.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.Service.StudentService;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Student;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/students")
	public ResponseEntity<StudentDto> addNewStudent(@RequestBody Student student)
	{
		return ResponseEntity.ok(studentService.addStudent(student));
	}
	
	@GetMapping(("/students"))
	public ResponseEntity<PageResponse<StudentDto>> getAllStudents(@RequestParam int pagenumber, @RequestParam int pagesize)
	{
		return ResponseEntity.ok(studentService.getAllStudents(pagenumber, pagesize));
	}
	
	@GetMapping(("/students/{rollnumber}"))
	public ResponseEntity<StudentDto> getStudentDetailsById(@PathVariable int rollnumber)
	{
		return ResponseEntity.ok(studentService.findStudentDetailsById(rollnumber));
	}
	
	@GetMapping(("/students/address"))
	public ResponseEntity<Address> getStudentAddressById(@RequestParam int rollnumber)
	{
		return ResponseEntity.ok(studentService.getStudentAddressById(rollnumber));
	}
	
	@PutMapping(("/students/{rollnumber}"))
	public ResponseEntity<Student> updateStudentCityById(@PathVariable int rollnumber, @RequestBody Address city)
	{
		return ResponseEntity.ok(studentService.updateStudentCityById(rollnumber, city));
	}
	
	@PutMapping(("/students/courses"))
	public ResponseEntity<StudentDto> assignCourses(@RequestParam int rollnumber, @RequestBody List<Integer> courses)
	{
		return ResponseEntity.ok(studentService.assignCourses(rollnumber, courses));
	}
}
