package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.EmployeeStatus;
import com.techlabs.cashmgmt.service.EmployeeService;

@RestController
@RequestMapping("/cashapp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;
	
	@GetMapping("/employees")
	public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) int pagenumber, @RequestParam(required = false) int pagesize)
	{
		if(name!=null)
			return ResponseEntity.ok(employeeservice.getAllEmployeesByFirstName(name,pagenumber,pagesize));
		return ResponseEntity.ok(employeeservice.getAllEmployees(pagenumber,pagesize));
	}

	@GetMapping("/employees/{employeeid}")
	public ResponseEntity<Employee> getEmployeeByEmployeeid(@PathVariable int employeeid) {
		
		return ResponseEntity.ok(employeeservice.getEmployeeByEmployeeId(employeeid));
	}

	@PostMapping("/employees")
	public String saveEmployee(@RequestBody Employee employee) {
		
		employeeservice.saveEmployee(employee);
		return "Employee Saved";
	}

	@DeleteMapping("/employees")
	public String deleteEmployee(@RequestParam int employeeid) {
		
		employeeservice.deleteEmployee(employeeid);
		return "Employee Deleted";
	}
	
	@GetMapping("/employees/kyc")
	public ResponseEntity<Page<Employee>> getAllEmployeesByKyc(@RequestParam EmployeeStatus employeestatus, @RequestParam(required = false) int pagenumber, @RequestParam(required = false) int pagesize)
	{
		return ResponseEntity.ok(employeeservice.getAllEmployeesByEmployeeStatus(employeestatus,pagenumber,pagesize));
	}
	
}

