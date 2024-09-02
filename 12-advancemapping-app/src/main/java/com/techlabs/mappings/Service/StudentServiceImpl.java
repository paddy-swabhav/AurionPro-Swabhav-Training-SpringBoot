package com.techlabs.mappings.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public StudentDto addStudent(Student student) {
		
		return toStudentDtoMApper(studentRepo.save(student));
	}

	@Override
	public PageResponse<StudentDto> getAllStudents(int pagenumber,int pagesize) {
		
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<StudentDto> studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setLastPage(studentPage.isLast());
		
		List<StudentDto> studentDtoList = new ArrayList<>();
		
		studentPage.getContent().forEach((student)->{
			studentDtoList.add(toStudentDtoMApper(student));
		});
		
		studentPageResponse.setContent(studentDtoList);

		return studentPageResponse;
	}

	@Override
	public StudentDto findStudentDetailsById(int rollnumber) {
		
		Student student = studentRepo.findById(rollnumber).orElseThrow(
						()-> new NullPointerException("Student Not Found"));

		return toStudentDtoMApper(student);
	}
	
	@Override
	public StudentDto assignCourses(int rollnumber, List<Integer> courseIds) {
	
		Student dbstudent = studentRepo.findById(rollnumber).orElseThrow(()->new NullPointerException());
		
		Set<Course> existingCourses = dbstudent.getCourses();
		if(existingCourses==null)
			existingCourses = new HashSet<>();
		
		
		Set<Course> coursesToAdd = new HashSet<>();
		courseIds.forEach((id)->{
			Course course = courseRepo.findById(id)
					.orElseThrow(()-> new NullPointerException());
		
		List<Student> existingStudents = course.getStudents();
		if(existingStudents==null)
			existingStudents = new ArrayList<>();
		
		existingStudents.add(dbstudent);
		coursesToAdd.add(course);
		
		});
		
		existingCourses.addAll(coursesToAdd);
		dbstudent.setCourses(existingCourses);
		
		return toStudentDtoMApper(studentRepo.save(dbstudent));
	}

	@Override
	public Address getStudentAddressById(int rollnumber) {
		
		Student dbStudent;
		Optional<Student> optionalStudent = studentRepo.findById(rollnumber);
		if(!optionalStudent.isPresent())
			return null;
		dbStudent = optionalStudent.get();
		return dbStudent.getAddress();
	}

	@Override
	public Student updateStudentCityById(int rollnumber, Address city) {
		Student dbStudent;
		Optional<Student> optionalStudent = studentRepo.findById(rollnumber);
		if(!optionalStudent.isPresent())
			return null;
		dbStudent = optionalStudent.get();  // saving the retrived Student value in dbStudent 

		Address address = dbStudent.getAddress();
		address.setCity(city.getCity());
		dbStudent.setAddress(address);
		
		studentRepo.save(dbStudent);

		return dbStudent;
	}
	
	
	
	
	//MAPPERS
	@Override
	public StudentDto toStudentDtoMApper(Student student)
	{
		StudentDto studentDto = new StudentDto();
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollnumber(student.getRollnumber());
		
		return studentDto;
	}
	
	@Override
	public Student toStudentMApper(StudentDto studentDto)
	{
		Student student = new Student();
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());
		
		return student;
	}


}
