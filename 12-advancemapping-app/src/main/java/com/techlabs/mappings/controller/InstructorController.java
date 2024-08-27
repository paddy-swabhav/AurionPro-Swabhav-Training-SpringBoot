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

import com.techlabs.mappings.Service.InstructorService;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Instructor;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructor")
	public ResponseEntity<InstructorDto> addInstructor(@RequestBody InstructorDto instructor)
	{
		return ResponseEntity.ok(instructorService.addInstructor(instructor));
	}
	
	@PutMapping("/instructor/courses")
	public ResponseEntity<Instructor> allocateCourses(@RequestParam int instructorId,@RequestBody List<Integer> courseIds)
	{
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId,courseIds));
	}
	
	@GetMapping("/instructor")
	public ResponseEntity<List<InstructorDto>> getAllInstructors(@RequestParam int pagenumber,@RequestParam int pagesize)
	{
		return ResponseEntity.ok(instructorService.getAllInstructors(pagenumber,pagesize));
	}
	
	@GetMapping("/instructor/student/{intstructorId}")
	public ResponseEntity<List<StudentDto>> getstudentsByInstructorId(@PathVariable int intstructorId)
	{
		return ResponseEntity.ok(instructorService.getstudentsByInstructorId(intstructorId));
	}
}
