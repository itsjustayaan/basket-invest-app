package com.working.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table
public class InvestorAndBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ibId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;
    
    @Column
    private int quantity;
    
//    @Column
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateBought;

    public InvestorAndBasket(){
    	
    }
    
    public InvestorAndBasket(Investor investor, Basket basket, int quantity) {
		super();
		this.investor = investor;
		this.basket = basket;
		this.quantity = quantity;
	}
    
    public int getId() {
        return ibId;
    }

	public void setId(int id) {
        this.ibId = id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
//    public Date getDateBought() {
//        return dateBought;
//    }
//
//    public void setDateBought(Date dateBought) {
//        this.dateBought = dateBought;
//    }
}
