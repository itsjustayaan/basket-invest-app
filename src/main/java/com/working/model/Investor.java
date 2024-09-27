package com.working.model;
 

import java.util.List;

import com.working.authentication.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Investor {

	@Id
	@Column(name = "investor_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int investorId;
	
	@Column
	private String investorName;

	@Column(insertable=false, updatable=false)
	private String email;
	
	@Column
	private String investorPassword;
	
	@Column
	private double investorBalance;
	
	@OneToOne
	@JoinColumn(name="email", referencedColumnName="email")
	private Roles role;

	public Investor(int investorId, String investorName, String investorEmail, String investorPassword,
			double investorBalance) {
		super();
		this.investorId = investorId;
		this.investorName = investorName;
		this.email = investorEmail;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String investorEmail) {
		this.email = investorEmail;
	}

	public String getInvestorPassword() {
		return investorPassword;
	}

	public void setInvestorPassword(String investorPassword) {
		this.investorPassword = investorPassword;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public double getInvestorBalance() {
		return investorBalance;
	}

	public void setInvestorBalance(double investorBalance) {
		this.investorBalance = investorBalance;
	}
}
