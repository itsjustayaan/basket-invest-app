package com.working.model;

public class Sell {
	
	private int investorId;
	private int basketId;
	private int quantity;
	
	public Sell() {
		
	}
	
	public Sell(int investorId, int basketId, int quantity) {
		super();
		this.investorId = investorId;
		this.basketId = basketId;
		this.quantity = quantity;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public int getBasketId() {
		return basketId;
	}

	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
