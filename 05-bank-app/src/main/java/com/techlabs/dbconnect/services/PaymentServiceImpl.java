package com.techlabs.dbconnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.entity.PaymentMode;
import com.techlabs.dbconnect.entity.PaymentStatus;
import com.techlabs.dbconnect.repositorys.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public List<Payment> getAllPayments() {
		
		return paymentRepo.getAllPayments();
	}

	@Override
	public Payment getPaymentById(int paymentid) {
		
		return paymentRepo.getPaymentById(paymentid);
	}

	@Override
	public void addPayment(Payment payment) {
		
		paymentRepo.addPayment(payment);
	}

	@Override
	public void deletePaymentById(int paymentid) {
		
		paymentRepo.deletePaymentById(paymentid);
	}

	@Override
	public void updatePaymentById(Payment payment) {
		
		paymentRepo.updatePaymentById(payment);
	}

	@Override
	public List<Payment> getPaymentByStatus(PaymentStatus paymentstatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> getPaymentByMode(PaymentMode paymentmode) {
		// TODO Auto-generated method stub
		return null;
	}



	
}

