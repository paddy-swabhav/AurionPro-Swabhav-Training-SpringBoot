package com.techlabs.mappings.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	
	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {
		
		Instructor instructor = toInstructorMapper(instructorDto);
		
		instructorRepo.save(instructor);
		
		return toInstructorDtoMapper(instructor);
	}

	//MAPPERS
	private Instructor toInstructorMapper(InstructorDto instructorDto)
	{
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorDto.getInstructorName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		return instructor;
	}
	
	private InstructorDto toInstructorDtoMapper(Instructor instructor)
	{
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setInstructorName(instructor.getInstructorName());
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setQualification(instructor.getQualification());
		
		return instructorDto;
	}
	//MAPPERS
	
	
	
	@Override
	public Instructor allocateCourses(int instructorId, List<Integer> courseIds) {

		Instructor dbInstructor = getInstructor(instructorId);
		
		List<Course> dbCourses = courseService.getAllCourse(instructorId);

		
		List<Course> coursesToAdd = new ArrayList<>();
		
		courseIds.forEach((courseId)->{
			
			Course course = courseService.getCourseById(courseId);
			course.setInstructor(dbInstructor);
			coursesToAdd.add(course);
		});
		
		dbCourses.addAll(coursesToAdd);
		dbInstructor.setCourses(dbCourses);
		
		return instructorRepo.save(dbInstructor);
	}


	@Override
	public List<InstructorDto> getAllInstructors(int pagenumber, int pagesize) {
		
		 List<InstructorDto> instructors = new ArrayList<>();
		  
		  List<Instructor> dbInstructors=instructorRepo.findAll();
		  
		  dbInstructors.forEach((instructor)->{;
		  instructors.add(toInstructorDtoMapper(instructor));
		  });
		  return instructors;
	}

	@Override
	public InstructorDto getInstructorDto(int instructorId) {
		
		Instructor dbinstructor = new Instructor();
		Optional<Instructor> instructor = instructorRepo.findById(instructorId);
		dbinstructor = instructor.get();
		
		return toInstructorDtoMapper(dbinstructor);
	}

	@Override
	public Instructor getInstructor(int instructorId) {

		return instructorRepo.findById(instructorId).get();
	}
	
	@Override
	public List<CourseDto> getCoursesByInstructor(int instructorId) {
		
		return courseService.getAllCourseDto(instructorId);
	}

	@Override
	public List<StudentDto> getstudentsByInstructorId(int instructorId) {

	    List<Course> courses = courseService.getAllCourse(instructorId);
	    List<Student> students = new ArrayList<>();
	    courses.forEach(course -> {
	        students.addAll(course.getStudents());
	    });

	    List<StudentDto> studentDtos = new ArrayList<>();

	    students.forEach(student -> {
	        boolean exists = false;
	        for (StudentDto studentDto : studentDtos) 
	        {
	            if (student.getRollnumber() == studentDto.getRollnumber()) 
	            {
	                exists = true;
	                break;
	            }
	        }
	        
	        if (!exists) 
	        {
	            studentDtos.add(studentService.toStudentDtoMApper(student));
	        }
	    });

	    return studentDtos;
	}





}
