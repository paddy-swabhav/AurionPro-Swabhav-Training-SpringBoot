package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.dto.ClientDto;
import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.entity.KycStatus;
import com.techlabs.cashmgmt.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cashapp")
@Validated
public class ClientController {

    @Autowired
    private ClientService clientservice;
    
    @GetMapping("/clients")
    public ResponseEntity<Page<Client>> getAllClients(
            @RequestParam(required = false) String name, 
            @RequestParam(defaultValue = "0") int pagenumber, 
            @RequestParam(defaultValue = "10") int pagesize) {
        
        if (name != null) {
            return ResponseEntity.ok(clientservice.getAllClientsByCompanyName(name, pagenumber, pagesize));
        }
        return ResponseEntity.ok(clientservice.getAllClients(pagenumber, pagesize));
    }

    @GetMapping("/clients/{clientid}")
    public ResponseEntity<Client> getClientByClientid(@PathVariable int clientid) {
        return ResponseEntity.ok(clientservice.getClientByClientId(clientid));
    }

    @PostMapping("/clients")
    public Client saveClient(@Valid @RequestBody ClientDto client) {
       
    	return clientservice.saveClient(client);
     
    }

    @DeleteMapping("/clients")
    public String deleteClient(@RequestParam int clientid) {
        clientservice.deleteClient(clientid);
        return "Client Deleted";
    }
    
    @GetMapping("/clients/kyc")
    public ResponseEntity<Page<Client>> getAllClientsByKyc(
            @RequestParam KycStatus status, 
            @RequestParam(defaultValue = "0") int pagenumber, 
            @RequestParam(defaultValue = "10") int pagesize) {
        
        return ResponseEntity.ok(clientservice.getAllClientsByKycStatus(status, pagenumber, pagesize));
    }
}
