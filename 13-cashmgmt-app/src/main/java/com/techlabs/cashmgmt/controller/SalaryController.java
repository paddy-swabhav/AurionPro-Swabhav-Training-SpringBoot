package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.entity.Salary;
import com.techlabs.cashmgmt.service.SalaryService;

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
}
