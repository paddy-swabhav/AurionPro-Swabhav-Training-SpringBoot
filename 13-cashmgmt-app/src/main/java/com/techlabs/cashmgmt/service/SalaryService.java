package com.techlabs.cashmgmt.service;

import org.springframework.data.domain.Page;

import com.techlabs.cashmgmt.entity.Salary;

public interface SalaryService {

	public Salary addSalary(Salary salary);

	public Page<Salary> getAllSalary(int pagenumber, int pagesize);

	public Salary getSalaryBySalaryId(int salaryid);

}
