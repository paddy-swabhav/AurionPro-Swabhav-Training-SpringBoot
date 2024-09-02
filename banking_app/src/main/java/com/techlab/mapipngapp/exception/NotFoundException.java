package com.techlab.mapipngapp.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException{
	
	private String message;

	

	 

}
