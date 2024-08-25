package com.techlabs.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.Service.InstructorService;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructor")
	public ResponseEntity<Instructor> addInstructor(@RequestBody InstructorDto instructor)
	{
		return ResponseEntity.ok(instructorService.addInstructor(instructor));
	}
	
	@PutMapping("/instructor/courses")
	public ResponseEntity<Instructor> allocateCourses(@RequestParam int instructorId,@RequestBody List<Course> courses)
	{
		return ResponseEntity.ok(instructorService.allocateCourses(instructorId,courses));
	}
	
	@GetMapping("/instructor")
	public ResponseEntity<PageResponse<Instructor>> getAllInstructors(@RequestParam int pagenumber,@RequestParam int pagesize)
	{
		return ResponseEntity.ok(instructorService.getAllInstructors(pagenumber,pagesize));
	}
}
