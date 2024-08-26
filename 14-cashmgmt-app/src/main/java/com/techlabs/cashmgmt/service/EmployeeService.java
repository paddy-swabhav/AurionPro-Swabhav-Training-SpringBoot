package com.techlabs.cashmgmt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlabs.cashmgmt.entity.Bank;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.EmployeeStatus;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.entity.SalaryTransaction;

import jakarta.validation.Valid;

public interface EmployeeService {

public Page<Employee> getAllEmployees(int pagenumber,int pagesize);
	
	public Employee getEmployeeByEmployeeId(int employeeid);
	
	public Employee saveEmployee(@Valid Employee employee);
	
	public void deleteEmployee(int employeeid);
	
	public Page<Employee> getAllEmployeesByFirstName(String firstname,int pagenumber,int pagesize);

	public Page<Employee> getAllEmployeesByEmployeeStatus(EmployeeStatus employeestatus, int pagenumber, int pagesize);

	public SalaryAccount allocateBank(long bankAccountNumber, Bank bank);

	public SalaryAccount allocateTransactions(long accountNumber, List<SalaryTransaction> transactions);
}
