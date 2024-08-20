package com.techlabs.dbconnect.repositorys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.entity.PaymentMode;
import com.techlabs.dbconnect.entity.PaymentStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Payment> getAllPayments() {
		
		TypedQuery<Payment> query = manager.createQuery("SELECT c FROM Payment c",Payment.class);
		return query.getResultList();
	}

	@Override
	public Payment getPaymentById(int paymentid) {
		
		return manager.find(Payment.class, paymentid);
	}

	@Override
	public void addPayment(Payment payment) {
		
		manager.persist(payment);
	}

	@Override
	public void deletePaymentById(int paymentid) {
		
		 Query query = manager.createQuery("DELETE from Payment c where c.paymentid=:thepaymentid");
		 query.setParameter("thepaymentid", paymentid);
		 query.executeUpdate();
	}

	@Override
	public void updatePaymentById(Payment payment) {
		
		Payment existingPayment = manager.find(Payment.class, payment.getPaymentid());
	    if (existingPayment != null) {
	        manager.merge(payment);
	    } else {
	        System.out.println("Payment not found for update");
	    }
		
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

