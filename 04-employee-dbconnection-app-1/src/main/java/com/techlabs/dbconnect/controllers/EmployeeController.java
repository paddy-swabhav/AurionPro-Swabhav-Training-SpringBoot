package com.techlabs.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Employee;
import com.techlabs.dbconnect.services.EmployeeService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/EmployeeApp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;
	
	@GetMapping("/Employees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		return ResponseEntity.ok(employeeservice.getAllEmployees());
	}
	
	@PostMapping("/Employees")
	@Transactional
	public String addEmployee(@RequestBody Employee employee)
	{
		employeeservice.addEmployees(employee);
		return "Added";
	}

}
