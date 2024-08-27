package com.techlabs.bank.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageModel {

	 private String name;
	 private MultipartFile file;
}
