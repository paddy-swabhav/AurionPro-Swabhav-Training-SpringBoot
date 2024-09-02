package com.techlabs.bank.entity;

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
@Table(name = "roles")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {

	@Id
	@Column(name = "roleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@Column(name = "rolename")
	private String rolename;
}
