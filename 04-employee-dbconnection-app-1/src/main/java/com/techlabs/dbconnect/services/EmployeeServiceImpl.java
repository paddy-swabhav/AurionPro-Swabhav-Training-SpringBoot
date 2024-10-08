package com.techlabs.dbconnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Employee;
import com.techlabs.dbconnect.repositorys.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeerepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeerepo.getAllEmployees();
	}

	@Override
	public void addEmployees(Employee employee) {
		employeerepo.addEmployee(employee);
	}
	
}
