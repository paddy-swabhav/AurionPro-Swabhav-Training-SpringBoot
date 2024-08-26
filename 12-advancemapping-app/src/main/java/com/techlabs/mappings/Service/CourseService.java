package com.techlabs.mappings.Service;

import java.util.List;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

public interface CourseService {

	
	public Course addCourse(CourseDto coursedto);

	public Course allocateInstructor(int courseId, Instructor instructor);

	public PageResponse<Course> getAllCourse(int pagenumber, int pagesize);
	
	public List<CourseDto> getAllCourseDto(int instructorId);
	
	public List<Course> getAllCourse(int instructorId);

	CourseDto toCourseDto(Course course);

	Course toCourseMapper(CourseDto courseDto);
	
	Course getCourseById(int courseid);
}
