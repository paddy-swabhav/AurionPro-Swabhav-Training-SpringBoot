package com.techlabs.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.DocumentDto;
import com.techlabs.bank.dto.ImageModel;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Document;
import com.techlabs.bank.entity.KycStatus;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.DocumentRepository;
import com.techlabs.bank.service.CloudinaryService;
import com.techlabs.bank.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private DocumentRepository documentRepo;
    
    @Autowired
    private CustomerRepository customerRepo;


    @Override
    public ResponseEntity<Map> uploadImage(ImageModel imageModel) {
        try {
            if (imageModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Document document = new Document();
            document.setName(imageModel.getName());
            document.setUrl(cloudinaryService.uploadFile(imageModel.getFile(), "Bank_folder"));
            document.setStatus(imageModel.getStatus());
            document.setCustomer(customerRepo.findById(imageModel.getCustomerId()).orElseThrow(()-> new CustomerNotFoundException(imageModel.getCustomerId())));
            document.setStatus(KycStatus.Pending);
            
            if(document.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            documentRepo.save(document);
            
            return ResponseEntity.ok().body(Map.of("url", document.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    @Override
    public List<DocumentDto> getAllDocumentsByCustomerId(int customerId){
     
     Customer dbCustomer = customerRepo.findById(customerId).orElseThrow( ()->new CustomerNotFoundException(customerId) );
     
     
     List<DocumentDto> documentDtos = new ArrayList<>();
     for (Document dbDocument : dbCustomer.getDocuments()) {
      
      DocumentDto documentDto = new DocumentDto();
      documentDto.setDocumentId(dbDocument.getDocumentId());
      documentDto.setName(dbDocument.getName());
      documentDto.setUrl(dbDocument.getUrl());
      documentDto.setKyc(dbDocument.getStatus());
      
      documentDtos.add(documentDto);
      
     }
     return documentDtos;
    }


	@Override
	public DocumentDto updateKycOfDocument(int customerId, int documentId, KycStatus status) {
		
		Customer customer = customerRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
		Document dbDocument = new Document();
		
		List<Document> documents= customer.getDocuments();
		for(Document document : documents)
		{
			if(document.getDocumentId()==documentId)
				dbDocument=document;
		}
		
		dbDocument.setStatus(status);
		
		documentRepo.save(dbDocument);
		
		 DocumentDto documentDto = new DocumentDto();
	      documentDto.setDocumentId(dbDocument.getDocumentId());
	      documentDto.setName(dbDocument.getName());
	      documentDto.setUrl(dbDocument.getUrl());
	      documentDto.setKyc(dbDocument.getStatus());
	      		
		return documentDto;
	}
}
