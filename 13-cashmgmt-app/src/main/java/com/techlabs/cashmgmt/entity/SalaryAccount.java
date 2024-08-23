package com.techlabs.cashmgmt.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="salaryaccount")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryAccount {
	
	@Id
	@Column(name="bankaccountnumber")
	@NotNull(message = "Bank account number is required.")
	@Digits(integer = 12, fraction = 0, message = "Bank account number should be up to 12 digits without decimals.")
	private long bankAccountNumber;
	
	@Column(name="accountholdername")
	@NotBlank(message = "name is required.")
	@Pattern(regexp = "^[^0-9]*$", message = "name must not contain numbers.")
	private String accountHolderName;
}
