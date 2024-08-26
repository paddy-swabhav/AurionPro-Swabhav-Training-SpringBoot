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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="salary")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Salary {
	
	@Id
	@Column(name="salaryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryId;
	
	@Column(name="salaryMonth")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Salary Month should not contain numbers")
	@NotBlank(message = "Salary Month cannot be blank")
	private String salaryMonth;
	
	@Column(name="grossSalary")
	@NotNull(message = "Gross Salary cannot be null")
	@Positive(message = "Gross Salary must be positive")
	private double grossSalary;
	
	@Column(name="deductions")
	@NotNull(message = "Deductions cannot be null")
	@Positive(message = "Deductions must be positive")
	private double deductions;
	
	@Column(name="netSalary")
	private double netSalary;
	
	@Column(name="paymentDate")
	@NotNull(message = "Payment Date cannot be null")
	private Date paymentDate;
	
	@Column(name="status")
	@NotNull(message = "Salary Status cannot be null")
	@Enumerated(EnumType.STRING)
	private SalaryStatus status;
	
//	@JoinColumn(name="transactionId")
//	@OneToOne(cascade = CascadeType.ALL)
//	private SalaryTransaction salaryTransaction;

}
