package com.techlabs.cashmgmt.service;

import org.springframework.data.domain.Page;

import com.techlabs.cashmgmt.entity.Salary;
import com.techlabs.cashmgmt.entity.SalaryTransaction;

public interface SalaryService {

	public Salary addSalary(Salary salary);

	public Page<Salary> getAllSalary(int pagenumber, int pagesize);

	public Salary getSalaryBySalaryId(int salaryid);

	public SalaryTransaction allocateTransaction(int salaryId, SalaryTransaction salaryTransaction);

	public SalaryTransaction addSalaryTransaction(SalaryTransaction salaryTransaction);

	public Page<SalaryTransaction> getAllSalaryTransaction(int pagenumber, int pagesize);

}
