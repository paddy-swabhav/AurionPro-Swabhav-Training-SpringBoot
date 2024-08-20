package com.techlabs.dbconnect.services;

import java.util.List;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.entity.PaymentMode;
import com.techlabs.dbconnect.entity.PaymentStatus;

public interface PaymentService {


	public List<Payment> getAllPayments();
	
	public Payment getPaymentById(int paymentid);
	
	public void addPayment(Payment payment);
	
	public void deletePaymentById(int paymentid);
	
	public void updatePaymentById(Payment payment);
	
	public List<Payment> getPaymentByStatus(PaymentStatus paymentstatus);
	
	public List<Payment> getPaymentByMode(PaymentMode paymentmode);
}
