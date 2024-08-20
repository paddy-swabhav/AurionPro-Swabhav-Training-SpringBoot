package com.techlabs.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

	@Autowired
	private EntityManager manager;
	
	
	@Override
	public List<Student> getAllStudents() {
		
		TypedQuery<Student> query = manager.createQuery("SELECT s FROM Student s",Student.class);
		return query.getResultList();
	}


	@Override
	public Student getStudent(int rollnumber) {
		
		return manager.find(Student.class, rollnumber);
	}


	@Override
	public void addStudent(Student student) {
		
		manager.persist(student);
		
	}


	@Override
	public List<Student> getStudentsByName(String name) {
		
		TypedQuery<Student> query = manager.createQuery("SELECT s from Student s where s.name=:theName",Student.class);
		query.setParameter("theName", name);
		return query.getResultList();
	}


	@Override
	public void deleteStudent(int rollnumber) {
		
//		Student student = manager.find(Student.class, rollnumber);
//	    if (student != null) {
//	        manager.remove(student);
//	    }
	    
		 Query query = manager.createQuery("DELETE from Student s where s.rollno=:therollnumber");
		 query.setParameter("therollnumber", rollnumber);
		 query.executeUpdate();
		
	}

	
}
