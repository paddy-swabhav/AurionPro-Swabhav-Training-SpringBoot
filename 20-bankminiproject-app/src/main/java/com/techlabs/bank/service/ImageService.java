package com.techlabs.bank.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.techlabs.bank.dto.DocumentDto;
import com.techlabs.bank.dto.ImageModel;
import com.techlabs.bank.entity.KycStatus;

public interface ImageService {

	ResponseEntity<Map> uploadImage(ImageModel imageModel);

	List<DocumentDto> getAllDocumentsByCustomerId(int customerId);
	

	DocumentDto updateKycOfDocument(int customerId, int documentId, KycStatus status);

}