package com.techlabs.bank.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.techlabs.bank.dto.DocumentDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Document;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.service.CloudinaryService;

import jakarta.annotation.Resource;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{

	 @Resource
	    private Cloudinary cloudinary;
	 
	 @Autowired
	 private CustomerRepository customerRepo;

	    @Override
	    public String uploadFile(MultipartFile file, String folderName) {
	        try{
	            HashMap<Object, Object> options = new HashMap<>();
	            options.put("folder", folderName);
	            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
	            String publicId = (String) uploadedFile.get("public_id");
	            return cloudinary.url().secure(true).generate(publicId);

	        }catch (IOException e){
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	
}
