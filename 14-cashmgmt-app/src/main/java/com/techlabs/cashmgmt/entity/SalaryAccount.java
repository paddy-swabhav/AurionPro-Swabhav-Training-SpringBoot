package com.techlabs.cashmgmt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@Column(name="salaryAccountNumber")
	@NotNull(message = "Bank account number is required.")
	@Digits(integer = 12, fraction = 0, message = "Bank account number should be up to 12 digits without decimals.")
	private long bankAccountNumber;
	
	@Column(name="accountHolderName")
	@NotBlank(message = "name is required.")
	@Pattern(regexp = "^[^0-9]*$", message = "name must not contain numbers.")
	private String accountHolderName;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="bankId")
	private Bank bank;
	
	@OneToMany(mappedBy = "salaryAccount", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JsonIgnore
	private List<SalaryTransaction> salaryTransaction;
}
