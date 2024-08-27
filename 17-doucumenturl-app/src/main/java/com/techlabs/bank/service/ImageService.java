package com.techlabs.bank.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.techlabs.bank.model.ImageModel;

public interface ImageService {

	ResponseEntity<Map> uploadImage(ImageModel imageModel);

}
