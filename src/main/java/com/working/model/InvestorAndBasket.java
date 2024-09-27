package com.working.model;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table
public class InvestorAndBasket {
	
	// Added as Investor may be able to make a duplicate sale
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "investor_id", referencedColumnName = "investor_id")
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "basket_id")
    private Basket basket;
    
    @Column
    private int quantity;
    
    // As we need to calculate profits
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBought;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
	
    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }
}
