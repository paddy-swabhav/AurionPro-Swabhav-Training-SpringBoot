package com.techlabs.bank.dto;

import com.techlabs.bank.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DocumentDto {

	private int documentId;
	
	private String name;
	
	private String url;
	
	private KycStatus kyc;
}
