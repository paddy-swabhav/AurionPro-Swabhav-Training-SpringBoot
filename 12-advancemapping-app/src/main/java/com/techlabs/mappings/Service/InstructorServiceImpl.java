package com.techlabs.mappings.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	
	@Override
	public Instructor addInstructor(InstructorDto instructorDto) {
		
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorDto.getInstructorName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		
		return instructorRepo.save(instructor);
	}


	@Override
	public Instructor allocateCourses(int instructorId, List<Course> courses) {

		Instructor dbInstructor;
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		if(!optionalInstructor.isPresent())
			return null;
		dbInstructor = optionalInstructor.get();
		
		List<Course> dbCourses = dbInstructor.getCourses();
		
		
		courses.forEach((course)-> {
			
			Course temp = courseRepo.findById(course.getCourseId()).get();

			temp.setInstructor(dbInstructor);
			
			dbCourses.add(temp);
		});
		
		dbInstructor.setCourses(dbCourses);
		
		return instructorRepo.save(dbInstructor);
	}


	@Override
	public PageResponse<Instructor> getAllInstructors(int pagenumber, int pagesize) {
		
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		
		PageResponse<Instructor> instructorPageResponse = new PageResponse();
		instructorPageResponse.setTotalPages(instructorPage.getTotalPages());
		instructorPageResponse.setTotalElements(instructorPage.getTotalElements());
		instructorPageResponse.setSize(instructorPage.getSize());
		instructorPageResponse.setContent(instructorPage.getContent());
		instructorPageResponse.setLastPage(instructorPage.isLast());
		
		return instructorPageResponse;
	}

}
