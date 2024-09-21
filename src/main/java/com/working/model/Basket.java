package com.working.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Basket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int basketId;
	
	@JoinTable(name="InvestmentAdvisor", joinColumns=@JoinColumn(name="iaId"))
	private int iaId;
	private String basketName;
	private String basketSummary;
	
	@OneToMany(mappedBy = "basket", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Stock> stockList;
	
	public Basket(int basketId, int iaId, String basketName, String basketSummary, List<Stock> stockList) {
		super();
		this.basketId = basketId;
		this.iaId = iaId;
		this.basketName = basketName;
		this.basketSummary = basketSummary;
		this.stockList = stockList;
	}

	public int getBasketId() {
		return basketId;
	}

	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}

	public int getIaId() {
		return iaId;
	}

	public void setIaId(int iaId) {
		this.iaId = iaId;
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

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
}
