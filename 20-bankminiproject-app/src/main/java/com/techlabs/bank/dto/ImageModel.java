package com.techlabs.bank.dto;

import org.springframework.web.multipart.MultipartFile;

import com.techlabs.bank.entity.KycStatus;

import lombok.Data;

@Data
public class ImageModel {

	 private String name;
	 private MultipartFile file;
	 private int customerId;
	 private KycStatus status;
}
