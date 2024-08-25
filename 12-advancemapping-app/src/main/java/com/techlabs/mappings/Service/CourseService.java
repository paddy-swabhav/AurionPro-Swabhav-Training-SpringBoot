package com.techlabs.mappings.Service;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

public interface CourseService {

	
	public Course addCourse(CourseDto coursedto);

	public Course allocateInstructor(int courseId, Instructor instructor);

	public PageResponse<Course> getAllCourse(int pagenumber, int pagesize);
}
