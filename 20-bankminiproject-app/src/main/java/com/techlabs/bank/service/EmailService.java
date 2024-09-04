package com.techlabs.bank.service;

import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.EmailDetails;

public interface EmailService {
 
    // Method
    // To send an email with attachment
    void SendStatementMail(EmailDetails details);

	void sendAccountOpeningMail(String mailId, String firstName, long accountnumber);
	
	void sendCustomerRegistrationMail(String firstName, String userName, String mailId);

	void transferNotificationMail(TransactionDto transaction, String firstName, String mailId);

	void debitNotificationMail(TransactionDto transaction, String firstName, String mailId);

	void creditNotificationMail(TransactionDto transaction, String firstName, String mailId);
}
