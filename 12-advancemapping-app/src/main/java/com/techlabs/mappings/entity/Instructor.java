	// ONE TO MANY 


package com.techlabs.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="instructors")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Instructor {

	@Id
	@Column(name="instructorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;
	
	@Column(name="instructorName")
	private String instructorName;
	
	@Column(name="email")
	private String email;
	
	@Column(name = "qualification")
	private String qualification;
	
	@OneToMany(mappedBy = "instructor", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})		//variable name declared in the course table
	@JsonIgnore
	private List<Course> courses;
}
