package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.entity.Salary;
import com.techlabs.cashmgmt.service.SalaryService;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cashapp")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;
	
	@PostMapping("/salary")
	public Salary addSalary(@Valid @RequestBody Salary salary)
	{
		return salaryService.addSalary(salary);
	}
	
	@GetMapping("/salary")
	public ResponseEntity<Page<Salary>> getAllSalary(@RequestParam(required = false, defaultValue = "0") int pagenumber,
									@RequestParam(required = false, defaultValue = "10") int pagesize)
	{
		return ResponseEntity.ok(salaryService.getAllSalary(pagenumber, pagesize));
	}
	
	@GetMapping("/salary/{salaryid}")
	public ResponseEntity<Salary> getSalaryById(@PathVariable int salaryid)
	{
		return ResponseEntity.ok(salaryService.getSalaryBySalaryId(salaryid));
	}
}
