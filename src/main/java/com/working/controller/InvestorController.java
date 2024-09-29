package com.working.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.BasketDAO;
import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;
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
	
	@Autowired
	InvestorAndBasketDAO investorAndBasketDAO;
	
		
	@PutMapping("update")
	public ResponseEntity<String> updateInvestor(@RequestBody Investor investor){
		return investorService.updateInvestor(investor);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteInvestor(Principal principal){
		return investorService.deleteInvestor(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId());
	}
	
	@PutMapping("updateBalance")
	public ResponseEntity<String> updateBalance(@RequestBody Investor investor){
		return investorService.updateInvestorBalance(investor.getInvestorBalance());
	}
	
	@GetMapping("checkBalance")
	public ResponseEntity<String> getBalance(Principal principal,@RequestBody Investor investor){
		return investorService.getInvestorBalance(principal.getName());
	}
	
	 @PostMapping("sell")
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
		
	@PostMapping("buy")
	public ResponseEntity<String> setInvestorBasket(@RequestBody InvestorAndBasket investBasket){
		Investor inv = investorDAO.findById(5).get();
		Basket bs = basketDAO.findById(2).get();
		investBasket.setBasket(bs);
		investBasket.setInvestor(inv);
		investorAndBasketDAO.save(investBasket);
		return new ResponseEntity<>("Investor has Basket",HttpStatus.OK);
	}
 
}
