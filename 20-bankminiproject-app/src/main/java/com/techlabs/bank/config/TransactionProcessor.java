package com.techlabs.bank.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.techlabs.bank.entity.Transaction;

@Component
public class TransactionProcessor implements ItemProcessor<Transaction, Transaction>{

	@Override
	public Transaction process(Transaction item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}
	
}
