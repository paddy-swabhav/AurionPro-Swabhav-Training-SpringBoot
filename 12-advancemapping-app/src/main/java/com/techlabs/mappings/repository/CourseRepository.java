package com.techlabs.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

	public List<Course> findAllByInstructor(Instructor instructor);
	
}
