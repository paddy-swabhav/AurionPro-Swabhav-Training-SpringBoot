package com.techlabs.dbconnect.repositorys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Customer> getAllCustomers() {
		
		TypedQuery<Customer> query = manager.createQuery("SELECT c FROM Customer c",Customer.class);
		return query.getResultList();
	}

	@Override
	public Customer getCustomerById(int customerid) {
		
		return manager.find(Customer.class, customerid);
	}

	@Override
	public void addCustomer(Customer customer) {
		
		manager.persist(customer);
	}

	@Override
	public void deleteCustomerById(int customerid) {
		
		 Query query = manager.createQuery("DELETE from Customer c where c.customerid=:thecustomerid");
		 query.setParameter("thecustomerid", customerid);
		 query.executeUpdate();
	}

	@Override
	public void updateCustomerById(Customer customer) {
		
		Customer existingCustomer = manager.find(Customer.class, customer.getCustomerid());
	    if (existingCustomer != null) {
	        manager.merge(customer);
	    } else {
	        System.out.println("Customer not found for update");
	    }
		
	}

	@Override
	public List<Customer> getCustomerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
