package com.techlabs.bank.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.EmailDetails;
import com.techlabs.bank.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") 
	private String sender;
	
	@Override
	public void sendAccountOpeningMail(String mailId, String firstName,long accountnumber) {
		
		String mailBody = "Hey"+firstName+".\n Your Account has been Successfully created. \n Your Account Number is: "+accountnumber;
		
		try {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom(sender);
			mailMessage.setTo(mailId);
			mailMessage.setText(mailBody);
			mailMessage.setSubject("Account Openning Mail");
			
			javaMailSender.send(mailMessage);
			
			System.out.println("Mail Sent Succesfully");
		}
		catch (Exception e) {
			System.out.println("Error While sending Mail" + e);
		}
		
	}

	@Override
	public void SendStatementMail(EmailDetails details) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			
			 // Setting multipart as true for attachments to
            // be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			
			 mimeMessageHelper.setFrom(sender);
	         mimeMessageHelper.setTo(details.getRecipient());
	         mimeMessageHelper.setText(details.getMsgBody());
	         mimeMessageHelper.setSubject(details.getSubject());
	         
	         // Adding the attachment
	         FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
	         
	         mimeMessageHelper.addAttachment(file.getFilename(), file);
	         
	         // Sending the mail
	         javaMailSender.send(mimeMessage);
				System.out.println("Mail Sent Succesfully");
			}
			catch (Exception e) {
				System.out.println("Error While sending Mail" + e);
			}
			
	}

	@Override
	public void sendCustomerRegistrationMail(String firstName, String userName,String mailId) {
		
		String mailBody = "Hey "+firstName+".\n You have been Successfully Registered as an Customer with our bank."
				+ " \n Your UserName is: "+userName+""
				+ "\n Your PassWord is: your first name with first letter as Capital, followed by @1234 (e.g if first name is 'sova' then the password will be 'Sova@1234')";
		
		try {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom(sender);
			mailMessage.setTo(mailId);
			mailMessage.setText(mailBody);
			mailMessage.setSubject("Customer Registeration Mail");
			
			javaMailSender.send(mailMessage);
			
			System.out.println("Mail Sent Succesfully");
		}
		catch (Exception e) {
			System.out.println("Error While sending Mail" + e);
		}
		
	}
	
	
	@Override
	public void creditNotificationMail(TransactionDto transaction, String firstName, String mailId) {
		
		String mailBody = "Hey "+firstName+"."
				+ "\n Your account "+transaction.getAccountNumber() +" has been Successfully Credited with Rs."+transaction.getAmount();
		
		try {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom(sender);
			mailMessage.setTo(mailId);
			mailMessage.setText(mailBody);
			mailMessage.setSubject("Credit Transaction Notification");
			
			javaMailSender.send(mailMessage);
			
			System.out.println("Mail Sent Succesfully");
		}
		catch (Exception e) {
			System.out.println("Error While sending Mail" + e);
		}
		
	}
	
	
	@Override
	public void debitNotificationMail(TransactionDto transaction, String firstName, String mailId) {
		
		String mailBody = "Hey "+firstName+"."
				+ "\n Your account "+transaction.getAccountNumber()+" has been Successfully Debited with Rs."+transaction.getAmount();
		
		try {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom(sender);
			mailMessage.setTo(mailId);
			mailMessage.setText(mailBody);
			mailMessage.setSubject("D Transaction Notification");
			
			javaMailSender.send(mailMessage);
			
			System.out.println("Mail Sent Succesfully");
		}
		catch (Exception e) {
			System.out.println("Error While sending Mail" + e);
		}
		
	}
	
	
	@Override
	public void transferNotificationMail(TransactionDto transaction, String firstName, String mailId) {
		
		String mailBody = "Hey "+firstName+"."
				+ "\n You have successfully Transfered Rs."+transaction.getAmount()+" To "+transaction.getReceiverAccountNumber()+""
						+ "\n From your account "+transaction.getAccountNumber();
		
		try {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setFrom(sender);
			mailMessage.setTo(mailId);
			mailMessage.setText(mailBody);
			mailMessage.setSubject("Transfer Transaction Notification");
			
			javaMailSender.send(mailMessage);
			
			System.out.println("Mail Sent Succesfully");
		}
		catch (Exception e) {
			System.out.println("Error While sending Mail" + e);
		}
		
	}

}
