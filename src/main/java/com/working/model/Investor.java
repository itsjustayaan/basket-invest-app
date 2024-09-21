package com.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Investor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int i_Id;
	
	@Column
	private String iEmail;
	
	@Column
	private String iPassword;
	
	@Column
	private double iBalance;
	
	public Investor(int i_Id, String iEmail, String iPassword, double iBalance) {
		super();
		this.i_Id = i_Id;
		this.iEmail = iEmail;
		this.iPassword = iPassword;
		this.iBalance = iBalance;
	}

	public int getI_Id() {
		return i_Id;
	}

	public void setI_Id(int i_Id) {
		this.i_Id = i_Id;
	}

	public String getiEmail() {
		return iEmail;
	}

	public void setiEmail(String iEmail) {
		this.iEmail = iEmail;
	}

	public String getiPassword() {
		return iPassword;
	}

	public void setiPassword(String iPassword) {
		this.iPassword = iPassword;
	}

	public double getiBalance() {
		return iBalance;
	}

	public void setiBalance(double iBalance) {
		this.iBalance = iBalance;
	}
}
