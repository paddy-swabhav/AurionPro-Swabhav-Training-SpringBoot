package com.techlabs.dbconnect.repositorys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

	@Autowired
	private EntityManager manager;
	
	
	
	@Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = manager.createQuery("SELECT e FROM Employee e",Employee.class);
		return query.getResultList();
	}

	@Override
	public void addEmployee(Employee employee) {
		manager.persist(employee);
	}

}
