package com.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class InvestmentAdvisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iaId;
	
	@Column
	private String iaEmail;
	
	@Column
	private String iaPassword;
	
//	public InvestmentAdvisor() {
//		
//	}
	
	public InvestmentAdvisor(int ia_Id, String ia_Email, String ia_Password) {
		super();
		this.iaId = ia_Id;
		this.iaEmail = ia_Email;
		this.iaPassword = ia_Password;
	}

	public int getIaId() {
		return iaId;
	}

	public void setIaId(int iaId) {
		this.iaId = iaId;
	}

	public String getIaEmail() {
		return iaEmail;
	}

	public void setIaEmail(String iaEmail) {
		this.iaEmail = iaEmail;
	}

	public String getIaPassword() {
		return iaPassword;
	}

	public void setIaPassword(String iaPassword) {
		this.iaPassword = iaPassword;
	}

	@Override
	public String toString() {
		return "InvestmentAdvisor [iaId=" + iaId + ", iaEmail=" + iaEmail + ", iaPassword=" + iaPassword + "]";
	}
}
