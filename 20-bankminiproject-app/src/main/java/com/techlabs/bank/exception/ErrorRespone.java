package com.techlabs.bank.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorRespone {
	
	private int statusCode;
	private String errorMessage;
	private long timeStamp;

}
