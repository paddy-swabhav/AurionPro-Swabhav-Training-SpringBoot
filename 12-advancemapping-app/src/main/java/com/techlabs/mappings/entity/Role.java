package com.techlabs.mappings.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="role")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Role {

	@Id
	@Column(name = "roleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@Column(name = "roleName")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;
}
