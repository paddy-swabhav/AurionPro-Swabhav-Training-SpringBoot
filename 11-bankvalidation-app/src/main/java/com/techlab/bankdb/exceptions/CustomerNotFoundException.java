package com.techlab.bankdb.exceptions;

public class CustomerNotFoundException extends RuntimeException{

	
	public String getMessage() {
		return "Customer with given Id is not present";
	}
}
