package com.techlabs.cashmgmt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "bank")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Bank {

	@Id
	@Column(name = "bankId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	
	@Column(name = "bankName")
	@NotBlank(message = "Bank Name cannot be null")
	private String bankName;
	
	@Column(name="bankifscnumber")
	@NotBlank(message = "IFSC code is required.")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "IFSC code must be exactly 11 characters: the first 4 letters are alphabets, followed by 0, and then 6 alphanumeric characters.")
	private String bankIfscNumber;
	
	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SalaryAccount> salaryAccount;
}
