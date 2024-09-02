package com.techlabs.bank.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
	private int customerId;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "customer" , cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private List<Account> accounts;
	
	
	
}
