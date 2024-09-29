package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.BasketDAO;
import com.working.dao.InvestorDAO;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.Sell;
import com.working.services.Investor.InvestorService;

@RestController
@RequestMapping("investor")
public class InvestorController {
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestorDAO investorDAO;

	@Autowired
	BasketDAO basketDAO;
	
	
	@PostMapping("create")
	public ResponseEntity<String> setInvestor(@RequestBody Investor investor){
		if(investorService.existsByEmail(investor.getInvestorEmail())) {
			return new ResponseEntity<>("Email-ID already exists",HttpStatus.NOT_ACCEPTABLE);
		}
		return investorService.createInvestor(investor);
	}
	
	@PutMapping
	public ResponseEntity<String> updateInvestor(@RequestBody Investor investor){
		return investorService.updateInvestor(investor);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteInvestor(@RequestBody Investor investor){
		return investorService.deleteInvestor(investor.getInvestorId());
	}
	
	@PutMapping("/balance")
	public ResponseEntity<String> updateBalance(@RequestBody Investor investor){
		return investorService.updateInvestorBalance(investor.getInvestorBalance());
	}
	
	 @PostMapping("/sell")
	    public String sellBasket(@RequestBody Sell sell) {

	        Investor investor = investorDAO.findById(sell.getInvestorId()).orElseThrow(() -> new RuntimeException("Investor not found"));
	        Basket basket = basketDAO.findById(sell.getBasketId()).orElseThrow(() -> new RuntimeException("Basket not found"));

	        try {
	            investorService.sellBasket(investor, basket, sell.getQuantity());
	            return "Basket sold successfully.";
	        } catch (Exception e) {
	            return "Error: " + e.getMessage();
	        }
	    }
}
