package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.entity.KycStatus;
import com.techlabs.cashmgmt.service.ClientService;

@RestController
@RequestMapping("/cashapp")
public class ClientController {

	@Autowired
	private ClientService clientservice;
	
	@GetMapping("/clients")
	public ResponseEntity<Page<Client>> getAllClients(@RequestParam(required = false) String name, @RequestParam(required = false) int pagenumber, @RequestParam(required = false) int pagesize)
	{
		if(name!=null)
			return ResponseEntity.ok(clientservice.getAllClientsByCompanyName(name,pagenumber,pagesize));
		return ResponseEntity.ok(clientservice.getAllClients(pagenumber,pagesize));
	}

	@GetMapping("/clients/{clientid}")
	public ResponseEntity<Client> getClientByClientid(@PathVariable int clientid) {
		
		return ResponseEntity.ok(clientservice.getClientByClientId(clientid));
	}

	@PostMapping("/clients")
	public String saveClient(@RequestBody Client client) {
		
		clientservice.saveClient(client);
		return "Client Saved";
	}

	@DeleteMapping("/clients")
	public String deleteClient(@RequestParam int clientid) {
		
		clientservice.deleteClient(clientid);
		return "Client Deleted";
	}
	
	@GetMapping("/clients/kyc")
	public ResponseEntity<Page<Client>> getAllClientsByKyc(@RequestParam KycStatus status, @RequestParam(required = false) int pagenumber, @RequestParam(required = false) int pagesize)
	{
		return ResponseEntity.ok(clientservice.getAllClientsByKycStatus(status,pagenumber,pagesize));
	}
	
}
