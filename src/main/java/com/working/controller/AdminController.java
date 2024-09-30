package com.working.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;
import com.working.services.Investor.InvestorService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestmentAdvisorService investmentAdvisorService;
	
	@PostMapping("createAdvisor")
	public ResponseEntity<String> createAdvisor(@RequestBody InvestmentAdvisor investmentAdvisor){
		return investmentAdvisorService.createInvestmentAdvisor(investmentAdvisor);
	}
	
	@PostMapping("createInvestor")
	public ResponseEntity<String> setInvestor(@RequestBody Investor investor){
		if(investorService.existsByEmail(investor.getInvestorEmail())) {
			return new ResponseEntity<>("Email-ID already exists",HttpStatus.NOT_ACCEPTABLE);
		}
		return investorService.createInvestor(investor);
	}
	
	
}