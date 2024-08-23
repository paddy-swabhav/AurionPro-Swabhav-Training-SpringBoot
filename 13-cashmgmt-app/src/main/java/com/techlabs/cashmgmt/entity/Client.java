package com.techlabs.cashmgmt.entity;

import java.sql.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
}
