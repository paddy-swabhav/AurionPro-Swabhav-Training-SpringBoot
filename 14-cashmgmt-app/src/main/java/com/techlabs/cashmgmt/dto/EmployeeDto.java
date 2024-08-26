package com.techlabs.cashmgmt.dto;

import java.sql.Date;

import com.techlabs.cashmgmt.entity.EmployeeStatus;
import com.techlabs.cashmgmt.entity.SalaryAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private int employeeId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String email;
	
	private String position;

	private Date hireDate;

	private double salary;

	private SalaryAccount salaryAccount;

	private EmployeeStatus status;

}
