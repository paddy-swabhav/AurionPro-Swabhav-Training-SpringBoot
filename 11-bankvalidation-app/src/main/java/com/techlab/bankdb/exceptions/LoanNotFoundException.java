package com.techlab.bankdb.exceptions;

public class LoanNotFoundException extends RuntimeException{

	
	public String getMessage() {
		return "Loan with given Id is not present";
	}
}
