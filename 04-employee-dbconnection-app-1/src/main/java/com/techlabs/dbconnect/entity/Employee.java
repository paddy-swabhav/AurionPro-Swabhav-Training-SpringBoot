package com.techlabs.dbconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	
	@Column(name="empid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empid;
	@Column(name="name")
	private String name;
	@Column(name="designation")
	private String designation;
	@Column(name="age")
	private int age;
	
	
	public Employee()
	{
		
	}
	
	
	public Employee(int empid, String name, String designation, int age) {
		super();
		this.empid = empid;
		this.name = name;
		this.designation = designation;
		this.age = age;
	}
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
