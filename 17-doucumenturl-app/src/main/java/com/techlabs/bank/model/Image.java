package com.techlabs.bank.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Image {

	@Column(name = "docId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int docId;
	
	@Column(name = "name_image")
	private String name;
	
	@Column(name = "url_image")
	private String url;
}
