package com.techlabs.mappings.Service;

import java.util.List;

import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

public interface InstructorService {

	public Instructor addInstructor(InstructorDto instructordto);

	public Instructor allocateCourses(int instructorId, List<Course> courses);

	public PageResponse<Instructor> getAllInstructors(int pagenumber, int pagesize);
}
