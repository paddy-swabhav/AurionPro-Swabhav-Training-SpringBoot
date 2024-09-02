package com.techlabs.jwtsecured.entity;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private long mobileNumber;
}
