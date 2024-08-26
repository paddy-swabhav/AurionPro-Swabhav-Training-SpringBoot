package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.cashmgmt.entity.SalaryTransaction;

@Repository
public interface SalaryTransactionRepository extends JpaRepository<SalaryTransaction, Integer>{

}
