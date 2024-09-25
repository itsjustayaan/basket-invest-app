package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.model.InvestmentAdvisor;
import com.working.services.InvestmentAdvisorService;

@RestController
@RequestMapping("ia")
public class InvestmentAdvisorController {
	
	@Autowired
	 InvestmentAdvisorService investmentAdvisorService;
	
	@PostMapping
	public ResponseEntity<String> setInvestor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.createInvestmentAdvisor(investmentAdvisor);
	}

}
