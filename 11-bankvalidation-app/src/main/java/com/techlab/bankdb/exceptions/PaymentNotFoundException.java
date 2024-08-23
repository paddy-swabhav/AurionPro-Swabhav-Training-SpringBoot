package com.techlab.bankdb.exceptions;

public class PaymentNotFoundException extends RuntimeException{

	
	public String getMessage() {
		return "Payment with given Id is not present";
	}
}
