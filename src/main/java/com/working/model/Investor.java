package com.working.model;
 

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private int investorBalance;
	
    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    private List<InvestorAndBasket> investorAndBasketList;	
	public Investor() {
		
	}
	
	public Investor(String investorName, String investorEmail, String investorPassword,
			int investorBalance) {
		super();
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

	public int getInvestorBalance() {
		return investorBalance;
	}

	public void setInvestorBalance(int investorBalance) {
		this.investorBalance = investorBalance;
	}
	
	public List<InvestorAndBasket> getInvestorAndBasketList() {
		return investorAndBasketList;
	}

	public void setInvestorAndBasketList(List<InvestorAndBasket> investorAndBasketList) {
		this.investorAndBasketList = investorAndBasketList;
	}
}
