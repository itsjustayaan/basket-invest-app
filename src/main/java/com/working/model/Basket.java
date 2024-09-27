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
public class Basket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="basket_id")
	private int basketId;
	
	@Column
	private String basketName;
	
	@Column
	private String basketSummary;
	
	@Column
	private int fk_iaId;
	
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketAndStock> basketStockList;
  
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<InvestorAndBasket> investorAndBasketList;

	public Basket(String basketName, String basketSummary, InvestmentAdvisor investmentAdvisor) {
	super();
	this.basketName = basketName;
	this.basketSummary = basketSummary;
	}

	public int getBasketId() {
		return basketId;
	}

	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}

	public String getBasketName() {
		return basketName;
	}

	public void setBasketName(String basketName) {
		this.basketName = basketName;
	}

	public String getBasketSummary() {
		return basketSummary;
	}

	public void setBasketSummary(String basketSummary) {
		this.basketSummary = basketSummary;
	}

	public List<BasketAndStock> getBasketStockList() {
		return basketStockList;
	}

	public void setBasketStockList(List<BasketAndStock> basketStockList) {
		this.basketStockList = basketStockList;
	}

}
