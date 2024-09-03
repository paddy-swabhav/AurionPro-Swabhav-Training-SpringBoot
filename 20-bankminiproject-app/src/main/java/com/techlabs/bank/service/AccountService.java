package com.techlabs.bank.service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.dto.CustomerAccountsDto;
import com.techlabs.bank.dto.PageResponse;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    
    PageResponse<AccountDto> getAllAccounts(int pagenumber, int pagesize);

	PageResponse<CustomerAccountsDto> getAccountsForCustomer(int pageNo, int pageSize, int customerId);

}
