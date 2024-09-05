package com.techlabs.bank.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.TransactionRepository;
import com.techlabs.bank.service.EmailService;

import io.jsonwebtoken.io.IOException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
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

	
	public void exportTransactionsToCsv(List<Transaction> transaction, String filePath) {
        // Define the CSV file header
        String[] header = { "Transaction ID", "Amount", "Recipient Account", "Date Time", "Transaction Type" };
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            writer.writeNext(header);
            
            for (Transaction dto : transaction)
            	System.out.println(dto.getTransactionId());
            
            // Write data rows
            for (Transaction dto : transaction) {
            	
            	Account receiverAccount = dto.getReceiverAccount();
            	String receiver = "Self";
                if(receiverAccount != null)
                {
                	receiver = String.valueOf(dto.getReceiverAccount().getAccountNumber());
                }

            	
                String[] data = {
                    String.valueOf(dto.getTransactionId()),
                    String.valueOf(dto.getAmount()),

                    String.valueOf(receiver),
                    dto.getDate().toString(),
                    dto.getType().name()
                };
                writer.writeNext(data);
            }
            
            System.out.println("CSV file was created successfully at: " + filePath);

        } catch (IOException e) {
            System.err.println("Error occurred while writing CSV file: " + e.getMessage());
        }catch(Exception e) { 
          System.err.println("Error occurred while writing CSV file: " + e.getMessage());
        }
    }
	
	
	
	
	@Override
	public void SendStatementMail(long accountNumber) {
		
		Customer customer = customerRepo.findByAccounts_AccountNumber(accountNumber);
		if(customer==null)
			throw new RuntimeException("No account number associated with this number found");
		
		String mailId = customer.getEmail();
		String body = "You Trannsaction Statement for the account "+accountNumber;
		
		
		
		List<Transaction> transactions= transactionRepo.findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(accountNumber, accountNumber);
		
		exportTransactionsToCsv(transactions,"transaction.csv" );
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			
			 // Setting multipart as true for attachments to
            // be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			
			 mimeMessageHelper.setFrom(sender);
	         mimeMessageHelper.setTo(mailId);
	         mimeMessageHelper.setText(body);
	         mimeMessageHelper.setSubject("Transaction Statement");
	         
	         // Adding the attachment
	         FileSystemResource file = new FileSystemResource(new File("transaction.csv"));
	         
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
