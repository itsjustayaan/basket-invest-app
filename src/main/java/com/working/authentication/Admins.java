package com.working.authentication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Admins {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int adminId;
	
	@Column(insertable=false, updatable=false)
	private String email;
	
	@Column
	private String adminPass;
	
	@OneToOne
	@JoinColumn(name="email", referencedColumnName="email")
	private Roles role;
	
	public Admins(int adminId, String adminEmail, String adminPass) {
		super();
		this.adminId = adminId;
		this.email = adminEmail;
		this.adminPass = adminPass;
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminEmail() {
		return email;
	}
	public void setAdminEmail(String adminEmail) {
		this.email = adminEmail;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	
}
