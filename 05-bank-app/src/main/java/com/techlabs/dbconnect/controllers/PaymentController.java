package com.techlabs.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.services.PaymentService;

import jakarta.transaction.Transactional;

@RequestMapping("/bankapp")
@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentservice;
	
	@GetMapping("/payment")
	public List<Payment> getAllPayments() {
		
		return paymentservice.getAllPayments();
	}

	@GetMapping("/payment/{paymentid}")
	public Payment getPaymentById(@PathVariable int paymentid) {
		
		return paymentservice.getPaymentById(paymentid);
	}

	@PostMapping("/payment")
	@Transactional
	public String addPayment(@RequestBody Payment payment) {
		
		paymentservice.addPayment(payment);
		return "Payment added";
	}

	@DeleteMapping("/payment/{paymentid}")
	@Transactional
	public String deletePaymentById(@PathVariable int paymentid) {
		
		paymentservice.deletePaymentById(paymentid);
		return "Payment Deleted";
	}

	@PutMapping("/payment")
	@Transactional
	public String updatePaymentById(@RequestBody Payment payment) {
	
		paymentservice.updatePaymentById(payment);
		return "Payment Updated";
	}
	
	

	
}
