package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.BasketDAO;
import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;

@RestController
@RequestMapping("investorBasket")
public class InvestorAndBasketController {
	
	@Autowired
	InvestorAndBasketDAO invBas;
	
	@Autowired
	InvestorDAO invDAO;
	
	@Autowired
	BasketDAO basDAO;
	
	@PostMapping
	public ResponseEntity<String> setInvestorBasket(@RequestBody InvestorAndBasket investBasket){
		Investor inv = invDAO.findById(1).get();
		Basket bs = basDAO.findById(1).get();
		investBasket.setBasket(bs);
		investBasket.setInvestor(inv);
		invBas.save(investBasket);
		return new ResponseEntity<>("Investor has Basket",HttpStatus.OK);
	}
}
