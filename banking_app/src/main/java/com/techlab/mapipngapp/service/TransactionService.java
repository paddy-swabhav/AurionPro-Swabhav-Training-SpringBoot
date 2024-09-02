package com.techlab.mapipngapp.service;

import com.techlab.mapipngapp.dto.AdminTransactionDto;
import com.techlab.mapipngapp.dto.CustomerTransactionDto;
import com.techlab.mapipngapp.dto.PageResponse;

public interface TransactionService {

	PageResponse<AdminTransactionDto> getAllTransactions(int pageNo,int pageSize);
	PageResponse<CustomerTransactionDto> getTransactionByAccount(long accountNumber,int pageNo,int pageSize);
}
