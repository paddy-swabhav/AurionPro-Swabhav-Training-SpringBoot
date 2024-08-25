package com.techlabs.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.Service.CourseService;
import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

@RestController
@RequestMapping("/studentapp")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/course")
	public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto)
	{
		return ResponseEntity.ok(courseService.addCourse(courseDto));
	}
	
	@PutMapping("/course/instructors")
	public ResponseEntity<Course> allocateCourses(@RequestParam int courseId,@RequestBody Instructor instructor)
	{
		return ResponseEntity.ok(courseService.allocateInstructor(courseId,instructor));
	}
	
	@GetMapping("/course")
	public ResponseEntity<PageResponse<Course>> getAllInstructors(@RequestParam(defaultValue = "0") int pagenumber,@RequestParam(defaultValue = "10") int pagesize)
	{
		return ResponseEntity.ok(courseService.getAllCourse(pagenumber,pagesize));
	}
}
