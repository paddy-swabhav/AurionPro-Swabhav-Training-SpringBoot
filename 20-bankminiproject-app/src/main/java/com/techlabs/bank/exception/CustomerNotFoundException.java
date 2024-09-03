package com.techlabs.bank.exception;

public class CustomerNotFoundException extends RuntimeException{

    private final int customerId;

    public CustomerNotFoundException(int customerId) {
        this.customerId = customerId;
    }
	
	public String getMessage() {
		return "Customer with ID " + customerId + " is not present";
	}
}
