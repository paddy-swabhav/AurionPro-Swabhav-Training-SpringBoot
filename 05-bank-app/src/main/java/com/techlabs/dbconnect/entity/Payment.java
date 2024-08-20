package com.techlabs.dbconnect.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {

	@Column(name="paymentid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentid;
	@Column(name="paymentdate")
	private Date paymentdate;
	@Column(name="amount")
	private double amount;
	@Column(name="paymentmode")
	private PaymentMode paymentmode;
	@Column(name="paymentstatus")
	private PaymentStatus paymentstatus;
	
	public Payment()
	{
		
	}
	
	public Payment(int paymentid, Date paymentdate, double amount, PaymentMode paymentmode,
			PaymentStatus paymentstatus) {
		super();
		this.paymentid = paymentid;
		this.paymentdate = paymentdate;
		this.amount = amount;
		this.paymentmode = paymentmode;
		this.paymentstatus = paymentstatus;
	}

	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentMode getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(PaymentMode paymentmode) {
		this.paymentmode = paymentmode;
	}

	public PaymentStatus getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(PaymentStatus paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	
	
	
	
}
