package com.techlabs.cashmgmt.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
@Table(name="client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
	
	@Id
	@Column(name="clientid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	
	@Column(name="companyname")
	private String companyName;
	
	@Column(name="registrationnumber")
	private int registrationNumber;
	
	@Column(name="contactperson")
	private String contactPerson;
	
	@Column(name="contactemail")
	private String contactEmail;
	
	@Column(name="address")
	private String address;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private ClientStatus status;
	
	@Column(name="creationdate")
	@CreationTimestamp
	private Date creationDate;
	
	@Column(name="kycstatus")
	@Enumerated(EnumType.STRING)
	private KycStatus kycStatus;
	
}
