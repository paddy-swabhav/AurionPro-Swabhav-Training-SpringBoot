package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.cashmgmt.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer>{

}
