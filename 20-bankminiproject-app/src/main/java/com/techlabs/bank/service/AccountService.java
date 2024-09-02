package com.techlabs.bank.service;

import java.util.List;

import com.techlabs.bank.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    
    List<AccountDto> getAllAccounts();
}
