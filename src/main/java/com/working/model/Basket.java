package com.working.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="fk_iaId")
	private InvestmentAdvisor investmentAdvisor;
	
	@ManyToMany
	@JoinTable(
			name="Basket_and_Stock",
			joinColumns=@JoinColumn(name="basketId"),
			inverseJoinColumns=@JoinColumn(name="stockId")
			)
	private List<Stock> stockList;
	
	@ManyToMany
	@JoinTable(
			name="Basket_and_Investor",
			joinColumns=@JoinColumn(name="basketId"),
			inverseJoinColumns=@JoinColumn(name="investorId")
			)
	private List<Investor> investorList;
	
	// Can Replace The One Above
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<InvestorAndBasket> investorAndBasketList;


	public Basket(int basketId, String basketName, String basketSummary, InvestmentAdvisor investmentAdvisor,
			List<Stock> stockList) {
		super();
		this.basketId = basketId;
		this.basketName = basketName;
		this.basketSummary = basketSummary;
		this.investmentAdvisor = investmentAdvisor;
		this.stockList = stockList;
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

	public InvestmentAdvisor getInvestmentAdvisor() {
		return investmentAdvisor;
	}

	public void setInvestmentAdvisor(InvestmentAdvisor investmentAdvisor) {
		this.investmentAdvisor = investmentAdvisor;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}	
}
