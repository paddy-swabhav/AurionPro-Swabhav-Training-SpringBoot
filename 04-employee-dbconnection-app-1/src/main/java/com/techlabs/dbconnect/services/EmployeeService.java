package com.techlabs.dbconnect.services;

import java.util.List;

import com.techlabs.dbconnect.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public void addEmployees(Employee employee);
	
}
