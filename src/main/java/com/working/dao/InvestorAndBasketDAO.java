package com.working.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;

public interface InvestorAndBasketDAO extends JpaRepository<InvestorAndBasket,Integer> {
	
	 public InvestorAndBasket findByInvestorAndBasket(Investor investor, Basket basket);
}
