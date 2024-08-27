package com.techlabs.mappings.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "courses")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Course {

	@Id
	@Column(name = "courseId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int courseId;
	
	@Column(name = "courseName")
	public String courseName;
	
	@Column(name = "duration")
	public int duration;
	
	@Column(name = "fees")
	public double fees;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "instructorId")
	private Instructor instructor;
	
	@ManyToMany(mappedBy = "courses")
	List<Student> students;
}
