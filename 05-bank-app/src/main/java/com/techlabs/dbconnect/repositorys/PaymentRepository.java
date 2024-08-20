package com.techlabs.dbconnect.repositorys;

import java.util.List;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.entity.PaymentMode;
import com.techlabs.dbconnect.entity.PaymentStatus;

public interface PaymentRepository {
	
	public List<Payment> getAllPayments();
	
	public Payment getPaymentById(int paymentid);
	
	public void addPayment(Payment payment);
	
	public void deletePaymentById(int paymentid);
	
	public void updatePaymentById(Payment payment);
	
	public List<Payment> getPaymentByStatus(PaymentStatus paymentstatus);
	
	public List<Payment> getPaymentByMode(PaymentMode paymentmode);

}
