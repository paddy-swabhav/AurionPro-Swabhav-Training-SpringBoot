package com.techlabs.cashmgmt.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
	@NotBlank(message = "First name is required.")
	@Pattern(regexp = "^[^0-9]*$", message = "First name must not contain numbers.")
	private String firstName;

	@Column(name="lastname")
	@NotBlank(message = "Last name is required.")
	@Pattern(regexp = "^[^0-9]*$", message = "Last name must not contain numbers.")
	private String lastName;

	@Column(name="phonenumber")
	@NotNull(message = "Phone number is required.")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be 10 digits and start with a digit between 6 and 9.")
	private String phoneNumber;

	@Column(name="email")
	@NotBlank(message = "Email is required.")
	@Email(message = "Email should be valid.")
	private String email;

	@Column(name="position")
	@NotBlank(message = "Position is required.")
	private String position;

	@Column(name="hiredate")
	@NotNull(message = "Hire date is required.")
	@PastOrPresent(message = "Hire date cannot be in the future.")
	private Date hireDate;

	@Column(name="salary")
	@Min(value = 0, message = "Salary must be a positive number.")
	private double salary;

	@JoinColumn(name="bankaccountnumber")
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull(message = "Bank account number is required.")
	private SalaryAccount salaryAccount;

//	@Column(name="bankifscnumber")
//	@NotBlank(message = "IFSC code is required.")
//	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "IFSC code must be exactly 11 characters: the first 4 letters are alphabets, followed by 0, and then 6 alphanumeric characters.")
//	private String bankIfscNumber;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	@NotNull(message = "Employee status is required.")
	private EmployeeStatus status;
}
