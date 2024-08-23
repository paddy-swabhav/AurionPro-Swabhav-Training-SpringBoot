package com.techlabs.cashmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.entity.Salary;
import com.techlabs.cashmgmt.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	@Autowired
	private SalaryRepository salaryRepo;
	
	@Override
	public Salary addSalary(Salary salary) {
		
		Salary salarydb = salary;
		salarydb.setNetSalary(salarydb.getGrossSalary() - salarydb.getDeductions());
		
		return salaryRepo.save(salary);
	}

}
	