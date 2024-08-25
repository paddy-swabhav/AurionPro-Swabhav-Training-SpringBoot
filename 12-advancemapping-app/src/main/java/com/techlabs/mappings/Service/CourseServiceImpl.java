package com.techlabs.mappings.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.InstructorRepository;

@Service
class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;

	@Override
	public Course addCourse(CourseDto coursedto) {
		
		Course course = new Course();
		course.setCourseName(coursedto.getCourseName());
		course.setDuration(coursedto.getDuration());
		course.setFees(coursedto.getFees());

		return courseRepo.save(course);
	}

	@Override
	public Course allocateInstructor(int courseId, Instructor instructor) {
		
		Course course;
		Instructor dbinstructor;
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructor.getInstructorId());
		dbinstructor = optionalInstructor.get();
		
		
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		course = optionalCourse.get();
		
		
		
		course.setInstructor(dbinstructor);
		
		return courseRepo.save(course);
	}

	@Override
	public PageResponse<Course> getAllCourse(int pagenumber, int pagesize) {
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Course> coursePage = courseRepo.findAll(pageable);
		
		PageResponse<Course> coursePageResponse = new PageResponse();
		coursePageResponse.setTotalPages(coursePage.getTotalPages());
		coursePageResponse.setTotalElements(coursePage.getTotalElements());
		coursePageResponse.setSize(coursePage.getSize());
		coursePageResponse.setContent(coursePage.getContent());
		coursePageResponse.setLastPage(coursePage.isLast());
		
		return coursePageResponse;

	}
	
	
	
	
}
