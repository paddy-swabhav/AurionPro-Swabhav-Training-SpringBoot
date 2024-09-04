package com.techlabs.bank.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techlabs.bank.dto.DocumentDto;

public interface CloudinaryService {

	String uploadFile(MultipartFile file, String folderName);


}
