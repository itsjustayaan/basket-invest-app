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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class InvestmentAdvisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iaId;
	
	@Column(nullable=false)
	private String iaName;
	
	@Column(insertable=false, updatable=false)
	private String email;
	
	@Column(nullable=false)
	private String iaPassword;
	
	@OneToOne
	@JoinColumn(name="email", referencedColumnName="email")
	private Roles role;
	
	protected InvestmentAdvisor() {
		
	}
	
	public InvestmentAdvisor(String ia_Name, String ia_Email) {
		super();
		this.iaName = ia_Name;
		this.email = ia_Email;
	}

	
	
	public String getIaName() {
		return iaName;
	}

	public void setIaName(String iaName) {
		this.iaName = iaName;
	}
	
	public int getIaId() {
		return iaId;
	}

	public void setIaId(int iaId) {
		this.iaId = iaId;
	}

	public String getIaEmail() {
		return email;
	}

	public void setIaEmail(String iaEmail) {
		this.email = iaEmail;
	}


	@Override
	public String toString() {
		return "InvestmentAdvisor [iaId=" + iaId + ", iaEmail=" + email + "]";
	}

	public String getIaPassword() {
		return iaPassword;
	}

	public Roles getRole() {
		return role;
	}
}
