package com.techlabs.cashmgmt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.entity.KycStatus;
import com.techlabs.cashmgmt.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientrepo;
	
	@Override
	public Page<Client> getAllClients(int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Client> ClientPage = clientrepo.findAll(pageable);
		return ClientPage;
	}

	@Override
	public Client getClientByClientId(int clientid) {
		
		Client client=null;
		Optional<Client> client1 = clientrepo.findById(clientid);
		if(client1.isPresent())
			client = client1.get();
		
		return client;
	}

	@Override
	public void saveClient(Client client) {

		clientrepo.save(client);
	}

	@Override
	public void deleteClient(int clientid) {

		clientrepo.deleteById(clientid);
	}

	@Override
	public Page<Client> getAllClientsByCompanyName(String name, int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Client> ClientPage = clientrepo.findAllByCompanyName(name,pageable);
		return ClientPage;
	}
	
	@Override
	public Page<Client> getAllClientsByKycStatus(KycStatus status , int pagenumber, int pagesize) {

		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<Client> ClientPage = clientrepo.findAllByKycStatus(status,pageable);
		return ClientPage;
	}

}
