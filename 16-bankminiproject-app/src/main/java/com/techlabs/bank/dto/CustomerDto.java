package com.techlabs.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
