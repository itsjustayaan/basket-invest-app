package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.InvestorDAO;
import com.working.model.Investor;

@RestController
@RequestMapping("investor")
public class InvestorController {
	
	@Autowired
	InvestorDAO inv;
	
	@PostMapping
	public ResponseEntity<String> setInvestor(@RequestBody Investor investor){
		inv.save(investor);
		return new ResponseEntity<String>("Investor Saved", HttpStatus.OK);
	}
}
