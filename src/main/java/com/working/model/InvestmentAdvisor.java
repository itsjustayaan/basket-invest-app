package com.working.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class InvestmentAdvisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iaId;
	
	@Column(nullable=false)
	private String iaName;
	
	@Column(nullable=false)
	private String iaEmail;
	
	@Column(nullable=false)
	private String iaPassword;
	
	@Column
	@OneToMany(cascade=CascadeType.ALL, mappedBy="investmentAdvisor")
	private List<Basket> basketList;
	
	protected InvestmentAdvisor() {
		
	}
	
	public InvestmentAdvisor(String ia_Email, String ia_Password) {
		super();
		this.iaEmail = ia_Email;
		this.iaPassword = ia_Password;
	}

	
	
	public String getIaName() {
		return iaName;
	}

	public void setIaName(String iaName) {
		this.iaName = iaName;
	}

	public List<Basket> getBasketList() {
		return basketList;
	}

	public void setBasketList(List<Basket> basketList) {
		this.basketList = basketList;
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
