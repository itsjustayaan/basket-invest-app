package com.working.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.InvestmentAdvisorDAO;
import com.working.model.Basket;
import com.working.model.BasketAndStock;
import com.working.model.InvestmentAdvisor;
import com.working.services.Basket.BasketServiceImpl;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;

@RestController
@RequestMapping("ia")
public class InvestmentAdvisorController {
	
	@Autowired
	InvestmentAdvisorService investmentAdvisorService;
	
	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;
	
	@Autowired
	BasketServiceImpl basketImpl;
	
	@PostMapping("create")
	public ResponseEntity<String> createAdvisor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.createInvestmentAdvisor(investmentAdvisor);
	}
	@PutMapping("update")
	public ResponseEntity<String> updateAdvisor(Principal principal,@RequestBody InvestmentAdvisor investmentAdvisor){
		investmentAdvisor.setIaId(investmentAdvisorDAO.findByIaEmail(principal.getName()).get(0).getIaId());
		return investmentAdvisorService.updateInvestmentAdvisor(investmentAdvisor);
	}
	
	@PostMapping("createBasket")
	public ResponseEntity<String> setBasket(@RequestBody Basket basket){
		return basketImpl.createBasket(basket);
	}
	
}
