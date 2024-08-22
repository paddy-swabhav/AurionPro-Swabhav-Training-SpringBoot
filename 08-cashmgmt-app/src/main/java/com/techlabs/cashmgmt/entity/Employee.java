package com.techlabs.cashmgmt.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

	@Id
	@Column(name="employeeid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="phonenumber")
	private long phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="position")
	private String position;
	
	@Column(name="hiredate")
	private Date hireDate;

	@Column(name="salary")
	private double salary;
	
	@Column(name="bankaccountnumber")
	private long bankAccountNumber;
	
	@Column(name="bankifscnumber")
	private String bankIfscNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private EmployeeStatus status;
	
}
