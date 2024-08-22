package com.techlabs.cashmgmt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.EmployeeStatus;
import com.techlabs.cashmgmt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{


	@Autowired
	private EmployeeRepository employeerepo;
	
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
	public void saveEmployee(Employee employee) {

		employeerepo.save(employee);
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
}
