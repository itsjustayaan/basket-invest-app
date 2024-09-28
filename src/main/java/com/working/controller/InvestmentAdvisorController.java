package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.model.InvestmentAdvisor;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;

@RestController
@RequestMapping("ia")
public class InvestmentAdvisorController {
	
	@Autowired
	InvestmentAdvisorService investmentAdvisorService;
	
	@PostMapping
	public ResponseEntity<String> createAdvisor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.createInvestmentAdvisor(investmentAdvisor);
	}
	@PutMapping
	public ResponseEntity<String> updateAdvisor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.updateInvestmentAdvisor(investmentAdvisor);
	}
	@DeleteMapping
	public ResponseEntity<String> deleteInvestor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.deleteInvestmentAdvisor(investmentAdvisor.getIaId());
	}
}
