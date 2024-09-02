package com.techlabs.mappings.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.InstructorRepository;
import com.techlabs.mappings.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
//	@Autowired
//	private InstructorService instructorService; 
	
	private static final Logger Logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Override
	public Course addCourse(CourseDto coursedto) {
		
		Course course = new Course();
		course.setCourseName(coursedto.getCourseName());
		course.setDuration(coursedto.getDuration());
		course.setFees(coursedto.getFees());
		course = courseRepo.save(course);
		Logger.info("Course added Successfully: "+course.getCourseId());

		return course;
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

	@Override
	public List<CourseDto> getAllCourseDto(int instructorId) {
		
		List<Course> coursesDb = courseRepo.findAllByInstructor(instructorRepo.findById(instructorId).get());
		
		List<CourseDto> courses = new ArrayList<>();
		
		coursesDb.forEach((course)->{
			
			courses.add(toCourseDto(course));
		}
		);

		return courses;
	}

	@Override
	public CourseDto toCourseDto(Course course) {
		
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		return courseDto;
	}
	
	@Override
	public Course toCourseMapper(CourseDto courseDto) {
		
		Course course = new Course();
		course.setCourseId(courseDto.getCourseId());
		course.setCourseName(courseDto.getCourseName());
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		return course;
	}

	@Override
	public Course getCourseById(int courseid) {
		
		Optional<Course> optionalCourse = courseRepo.findById(courseid);
		if(!optionalCourse.isPresent())
		{
			Logger.error("Course with given id not found");
		return null;
		}
		Course dbcourse = optionalCourse.get();
		
		return dbcourse;
	}

	@Override
	public List<Course> getAllCourse(int instructorId) {

		List<Course> courses = courseRepo.findAllByInstructor(instructorRepo.findById(instructorId).get());
		return courses;
	}

	@Override
	public CourseDto assignStudents(int courseid, List<Integer> studentIds) {
		
		Course dbCourse = courseRepo.findById(courseid).orElseThrow(()-> new NullPointerException("Course does not exist")); 
		   
		  List<Student> existingStudents = dbCourse.getStudents(); 
		  if(existingStudents ==null) 
		   existingStudents = new ArrayList<>(); 
		   
		  List<Student> studentsToAdd = new ArrayList<>(); 
		   
		  studentIds.forEach((id)-> { 
		   Student student = studentRepo.findById(id).orElseThrow(()-> new NullPointerException("student not found")); 
		    
		   Set<Course> existingCourse = student.getCourses(); 
		   if(existingCourse ==null) 
		    existingCourse = new HashSet<>(); 
		    
		   existingCourse.add(dbCourse); 
		   studentsToAdd.add(student); 
		      
		  }); 
		   
		  existingStudents.addAll(studentsToAdd); 
		  dbCourse.setStudents(existingStudents); 
		   
		 
		  return toCourseDto(courseRepo.save(dbCourse)); 

	}


	
	
	
}
