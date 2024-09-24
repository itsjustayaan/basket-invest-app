package com.working.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Investor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int investorId;
	
	@Column
	private String investorName;
	
	@Column
	private String investorEmail;
	
	@Column
	private String investorPassword;
	
	@Column
	private double investorBalance;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="investorList")
	private List<Basket> basketList;

	public Investor(int investorId, String investorName, String investorEmail, String investorPassword,
			double investorBalance) {
		super();
		this.investorId = investorId;
		this.investorName = investorName;
		this.investorEmail = investorEmail;
		this.investorPassword = investorPassword;
		this.investorBalance = investorBalance;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getInvestorEmail() {
		return investorEmail;
	}

	public void setInvestorEmail(String investorEmail) {
		this.investorEmail = investorEmail;
	}

	public String getInvestorPassword() {
		return investorPassword;
	}

	public void setInvestorPassword(String investorPassword) {
		this.investorPassword = investorPassword;
	}

	public double getInvestorBalance() {
		return investorBalance;
	}

	public void setInvestorBalance(double investorBalance) {
		this.investorBalance = investorBalance;
	}
}
