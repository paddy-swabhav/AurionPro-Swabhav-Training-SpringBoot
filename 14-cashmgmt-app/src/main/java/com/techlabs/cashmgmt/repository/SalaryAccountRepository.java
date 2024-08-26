package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.cashmgmt.entity.SalaryAccount;

@Repository
public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Long>{

}
