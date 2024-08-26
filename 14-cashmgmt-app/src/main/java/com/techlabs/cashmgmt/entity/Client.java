package com.techlabs.cashmgmt.entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @Column(name = "clientid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "companyname")
    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @Column(name = "registrationnumber")
    private int registrationNumber;

    @Column(name = "contactperson")
    @NotBlank(message = "Contact person cannot be blank")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Contact person should not contain numbers")
    private String contactPerson;

    @Column(name = "contactNumber")
	@NotNull(message = "contact number is required")
    private long contactNumber;
    
    @Column(name = "contactemail")
    @NotBlank(message = "Contact email cannot be blank")
    @Email(message = "Email should be valid")
    private String contactEmail;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "creationdate")
    @CreationTimestamp
    @PastOrPresent(message = "Creation date cannot be in the future")
    private Date creationDate;

    @Column(name = "kycstatus")
    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus;
    
    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
