package com.techlabs.jwtsecured.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.jwtsecured.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
