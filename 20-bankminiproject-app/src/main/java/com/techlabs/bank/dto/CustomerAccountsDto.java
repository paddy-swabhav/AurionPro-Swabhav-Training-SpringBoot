package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountsDto {
	
	private int customerId;
    private long numberOfAccounts;
    private List<AccountDto> accountDtos;
}
