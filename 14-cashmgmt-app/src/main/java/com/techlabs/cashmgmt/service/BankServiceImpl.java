package com.techlabs.cashmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.entity.Bank;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankRepository bankRepository;
	
	@Override
	public Bank addBank(Bank bank) {
		
		return bankRepository.save(bank);
	}

	@Override
	public Page<Bank> getAllBanks(int pagenumber, int pagesize) {
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Bank> BankPage = bankRepository.findAll(pageable);
		return BankPage;
	}

}
