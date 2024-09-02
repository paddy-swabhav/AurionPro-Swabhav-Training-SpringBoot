package com.techlabs.bank.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "admin")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adminId")
	private int adminId;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name = "name")
	private String name;
	
}
