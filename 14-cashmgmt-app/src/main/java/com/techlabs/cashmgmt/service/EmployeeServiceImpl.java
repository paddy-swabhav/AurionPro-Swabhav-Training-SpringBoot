package com.techlabs.cashmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.entity.Bank;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.EmployeeStatus;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.entity.SalaryTransaction;
import com.techlabs.cashmgmt.repository.BankRepository;
import com.techlabs.cashmgmt.repository.EmployeeRepository;
import com.techlabs.cashmgmt.repository.SalaryAccountRepository;
import com.techlabs.cashmgmt.repository.SalaryTransactionRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{


	@Autowired
	private EmployeeRepository employeerepo;
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private SalaryTransactionRepository salaryTransactionRepo;
	
	@Override
	public Page<Employee> getAllEmployees(int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Employee> EmployeePage = employeerepo.findAll(pageable);
		return EmployeePage;
	}

	@Override
	public Employee getEmployeeByEmployeeId(int employeeid) {
		
		Employee employee=null;
		Optional<Employee> employee1 = employeerepo.findById(employeeid);
		if(employee1.isPresent())
			employee = employee1.get();
		
		return employee;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeerepo.save(employee);
	}

	@Override
	public void deleteEmployee(int employeeid) {

		employeerepo.deleteById(employeeid);
	}

	@Override
	public Page<Employee> getAllEmployeesByFirstName(String name, int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Employee> EmployeePage = employeerepo.findAllByFirstName(name,pageable);
		return EmployeePage;
	}
	
	@Override
	public Page<Employee> getAllEmployeesByEmployeeStatus(EmployeeStatus employeestatus , int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Employee> EmployeePage = employeerepo.findAllByStatus(employeestatus,pageable);
		return EmployeePage;
	}

	@Override
	public SalaryAccount allocateBank(long bankAccountNumber, Bank bank) {
		
		SalaryAccount salaryAccount = new SalaryAccount();
		Bank dbbank = new Bank();
		
		Optional<Bank> optionalBank = bankRepository.findById(bank.getBankId());
		dbbank = optionalBank.get();
		
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(bankAccountNumber);
		salaryAccount = optionalSalaryAccount.get();
		
		salaryAccount.setBank(dbbank);
		
		return salaryAccountRepo.save(salaryAccount);
	}

	@Override
	public SalaryAccount allocateTransactions(long accountNumber, List<SalaryTransaction> transactions) {
		
		SalaryAccount dbSalaryAccount;
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
		if(!optionalSalaryAccount.isPresent())
			return null;
		dbSalaryAccount = optionalSalaryAccount.get();
		
		List<SalaryTransaction> dbTransactions = dbSalaryAccount.getSalaryTransaction();
		
		
		transactions.forEach((transaction)-> {
			
			SalaryTransaction temp = salaryTransactionRepo.findById(transaction.getTransactionId()).get();

			temp.setSalaryAccount(dbSalaryAccount);
			
			dbTransactions.add(temp);
		});
		
		dbSalaryAccount.setSalaryTransaction(dbTransactions);
		
		return salaryAccountRepo.save(dbSalaryAccount);
	}
}
