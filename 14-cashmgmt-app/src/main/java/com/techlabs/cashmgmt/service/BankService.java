package com.techlabs.cashmgmt.service;

import org.springframework.data.domain.Page;

import com.techlabs.cashmgmt.entity.Bank;

public interface BankService{

	public Bank addBank(Bank bank);

	public Page<Bank> getAllBanks(int pagenumber, int pagesize);

}
