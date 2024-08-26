package com.techlabs.cashmgmt.service;

import org.springframework.data.domain.Page;

import com.techlabs.cashmgmt.dto.ClientDto;
import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.entity.KycStatus;

import jakarta.validation.Valid;

public interface ClientService {

	public Page<Client> getAllClients(int pagenumber,int pagesize);
	
	public Client getClientByClientId(int employeeid);
	
	public Client saveClient(@Valid ClientDto client);
	
	public void deleteClient(int employeeid);
	
	public Page<Client> getAllClientsByCompanyName(String name,int pagenumber,int pagesize);

	public Page<Client> getAllClientsByKycStatus(KycStatus status, int pagenumber, int pagesize);

}
