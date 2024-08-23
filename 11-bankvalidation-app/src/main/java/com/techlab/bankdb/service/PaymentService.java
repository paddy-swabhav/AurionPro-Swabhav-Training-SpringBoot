package com.techlab.bankdb.service;

import java.util.List;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Payment;
import com.techlab.bankdb.entity.PaymentMode;
import com.techlab.bankdb.entity.PaymentStatus;


public interface PaymentService {
	PageResponse<Payment> getAllPayments(int pageNo,int pageSize);

	Payment getPaymentById(int paymentId);

	Payment addUpdatePayment(Payment payment);

	void deletePaymentById(int paymentId);

	

	PageResponse<Payment> getPaymentsByStatus(PaymentStatus status,int pageNo,int pageSize);
	
	PageResponse<Payment> getPaymentsByMode(PaymentMode mode,int pageNo,int pageSize);
}
