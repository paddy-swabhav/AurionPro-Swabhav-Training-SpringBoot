package com.techlab.bankdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.bankdb.entity.Customer;

public interface CustomerRepository extends  JpaRepository<Customer, Integer>{

}

