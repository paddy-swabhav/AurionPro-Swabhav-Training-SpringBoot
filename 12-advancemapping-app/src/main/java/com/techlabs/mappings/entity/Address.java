package com.techlabs.mappings.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="addressId")
	private int addressId;
	
	@Column(name="buildingName")
	private String buildingName;
	
	@Column(name="city")
	private String city;
	
	@Column(name="pincode")
	private int pincode;
	
}
