package com.techlabs.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mail.entity.EmailDetails;
import com.techlabs.mail.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details)
	{
		return emailService.sendSimpleMail(details);
	}
	
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details)
	{
		return emailService.sendMailWithAttachment(details);
	}
}
