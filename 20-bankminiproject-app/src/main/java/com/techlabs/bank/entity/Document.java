package com.techlabs.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "documents")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Document {

	@Column(name = "documentId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int documentId;
	
	@Column(name = "name_image")
	@NotBlank(message = "Document Name Cannot be Blank")
	@NotNull(message = "Document Name Cannot be Null")
	private String name;
	
	@Column(name = "url_image")
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column
	private KycStatus status;
}
