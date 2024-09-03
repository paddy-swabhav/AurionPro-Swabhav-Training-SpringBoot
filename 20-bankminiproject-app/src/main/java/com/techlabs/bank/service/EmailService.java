package com.techlabs.bank.service;

import com.techlabs.bank.entity.EmailDetails;

public interface EmailService {
 
    // Method
    // To send an email with attachment
    void SendStatementMail(EmailDetails details);

	void sendAccountOpeningMail(String mailId, String firstName, long accountnumber);
	
	void sendCustomerRegistrationMail(String firstName, String userName, String mailId);
}
