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

import com.working.model.Investor;
import com.working.services.Investor.InvestorService;

@RestController
@RequestMapping("investor")
public class InvestorController {
	
	@Autowired
	InvestorService investorService;
	
	@PostMapping
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
}
