package com.techlabs.cashmgmt.dto;

import java.sql.Date;

import com.techlabs.cashmgmt.entity.ClientStatus;
import com.techlabs.cashmgmt.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private int clientId;

    private String companyName;

    private int registrationNumber;

    private String contactPerson;

    private String contactEmail;
    
    private long contactNumber;

    private String address;

    private ClientStatus status;

    private Date creationDate;

    private KycStatus kycStatus;
}
