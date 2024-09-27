package com.working.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="fk_iaId")
	private InvestmentAdvisor investmentAdvisor;
	
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketAndStock> basketStockList;
	
//	@ManyToMany
//	@JoinTable(
//			name="Basket_and_Stock",
//			joinColumns=@JoinColumn(name="basketId"),
//			inverseJoinColumns=@JoinColumn(name="isin")
//			)
//	private List<Stock> stockList;
	
// 	@ManyToMany
// 	@JoinTable(
// 			name="Basket_and_Investor",
// 			joinColumns=@JoinColumn(name="basketId"),
// 			inverseJoinColumns=@JoinColumn(name="investorId")
// 			)
// 	private List<Investor> investorList;
  
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<InvestorAndBasket> investorAndBasketList;

	public Basket(String basketName, String basketSummary, InvestmentAdvisor investmentAdvisor) {
	super();
	this.basketName = basketName;
	this.basketSummary = basketSummary;
	this.investmentAdvisor = investmentAdvisor;
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

	public List<BasketAndStock> getBasketStockList() {
		return basketStockList;
	}

	public void setBasketStockList(List<BasketAndStock> basketStockList) {
		this.basketStockList = basketStockList;
	}

	public List<Investor> getInvestorList() {
		return investorList;
	}

	public void setInvestorList(List<Investor> investorList) {
		this.investorList = investorList;
	}
}
