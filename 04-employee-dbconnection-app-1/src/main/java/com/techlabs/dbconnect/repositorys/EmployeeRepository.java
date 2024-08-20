package com.techlabs.dbconnect.repositorys;

import java.util.List;

import com.techlabs.dbconnect.entity.Employee;

public interface EmployeeRepository {
	
	public List<Employee> getAllEmployees();
	
	public void addEmployee(Employee employee);
}
