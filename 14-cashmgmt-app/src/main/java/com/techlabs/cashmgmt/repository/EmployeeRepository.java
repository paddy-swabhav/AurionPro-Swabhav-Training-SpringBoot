package com.techlabs.cashmgmt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.EmployeeStatus;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Page<Employee> findAllByFirstName(String name, Pageable pageable);

	Page<Employee> findAllByStatus(EmployeeStatus employeestatus, Pageable pageable);

}
