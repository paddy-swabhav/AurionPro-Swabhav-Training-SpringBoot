package com.techlabs.jwtsecured.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.jwtsecured.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	
}
