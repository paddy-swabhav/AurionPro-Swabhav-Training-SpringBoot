package com.techlabs.mappings.entity;

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


@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rollnumber")
	private int rollnumber;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address address;
	
}
