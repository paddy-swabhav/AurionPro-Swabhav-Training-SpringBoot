package com.techlabs.jpacrud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.jpacrud.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{


	public Page<Student> findByName(String name, Pageable pageable);
}
