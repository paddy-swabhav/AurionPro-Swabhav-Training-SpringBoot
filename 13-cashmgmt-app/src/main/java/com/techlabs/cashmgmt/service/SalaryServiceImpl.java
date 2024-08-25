package com.techlabs.cashmgmt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.entity.Employee;
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
	
	@Override
	public Page<Salary> getAllSalary(int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Salary> SalaryPage = salaryRepo.findAll(pageable);
		return SalaryPage;
	}

	
	@Override
	public Salary getSalaryBySalaryId(int salaryid) {
		
		Salary salary=null;
		Optional<Salary> salary1 = salaryRepo.findById(salaryid);
		if(salary1.isPresent())
			salary = salary1.get();
		
		return salary;
	}

}
	