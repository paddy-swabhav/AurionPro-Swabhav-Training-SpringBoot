package com.techlabs.dbconnect.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loans")
public class Loan {

	@Column(name="loanid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanid;
	@Column(name="loanamount")
	private double loanamount;
	@Column(name="interestrate")
	private int interestrate;
	@Column(name="loanterm")
	private int loanterm;
	@Column(name="startdate")
	private Date startdate;
	@Column(name="enddate")
	private Date enddate;
	@Column(name="loanstatus")
	private LoanStatus loanstatus;
	
	public Loan()
	{
		
	}
	
	public Loan(int loanid, double loanamount, int interestrate, int loanterm, Date startdate, Date enddate,
			LoanStatus loanstatus) {
		super();
		this.loanid = loanid;
		this.loanamount = loanamount;
		interestrate = interestrate;
		this.loanterm = loanterm;
		this.startdate = startdate;
		this.enddate = enddate;
		this.loanstatus = loanstatus;
	}

	public int getLoanid() {
		return loanid;
	}

	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}

	public double getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(double loanamount) {
		this.loanamount = loanamount;
	}

	public int getinterestrate() {
		return interestrate;
	}

	public void setinterestrate(int interestrate) {
		interestrate = interestrate;
	}

	public int getLoanterm() {
		return loanterm;
	}

	public void setLoanterm(int loanterm) {
		this.loanterm = loanterm;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public LoanStatus getLoanstatus() {
		return loanstatus;
	}

	public void setLoanstatus(LoanStatus loanstatus) {
		this.loanstatus = loanstatus;
	}
	
	
	
}
