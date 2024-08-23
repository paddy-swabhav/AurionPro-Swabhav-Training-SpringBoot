package com.techlabs.mappings.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentAddressDto;
import com.techlabs.mappings.dto.StudentDetails;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepo.save(student);
	}

	@Override
	public PageResponse<Student> getAllStudents(int pagenumber,int pagesize) {
		
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<Student> studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		
		return studentPageResponse;
	}

	@Override
	public StudentDetails findStudentDetailsById(int rollnumber) {
		
		Optional<Student> optionalStudent = studentRepo.findById(rollnumber);
		
		Student student = optionalStudent.get();
		
		StudentDetails studentDetail = new StudentDetails();
		studentDetail.setRollnumber(student.getRollnumber());
		studentDetail.setName(student.getName());
		studentDetail.setAge(student.getAge());
		return studentDetail;
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
		
//		Address dbAddress = dbStudent.getAddress();   // saving the address of retrived Student in Address object
//		dbAddress.setCity(city);     // setting the change city in address
//		
//		dbStudent.setAddress(dbAddress);  //  setting the changed address in the student object
//		
//		studentRepo.save(dbStudent);  // saving the updates student object to save in database
		
		Address address = dbStudent.getAddress();
		address.setCity(city.getCity());
		dbStudent.setAddress(address);
		
		studentRepo.save(dbStudent);

		return dbStudent;
	}
	
	
	
}
