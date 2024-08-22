package com.techlabs.cashmgmt.service;

import org.springframework.data.domain.Page;

import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.EmployeeStatus;

public interface EmployeeService {

public Page<Employee> getAllEmployees(int pagenumber,int pagesize);
	
	public Employee getEmployeeByEmployeeId(int employeeid);
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(int employeeid);
	
	public Page<Employee> getAllEmployeesByFirstName(String firstname,int pagenumber,int pagesize);

	public Page<Employee> getAllEmployeesByEmployeeStatus(EmployeeStatus employeestatus, int pagenumber, int pagesize);
}
