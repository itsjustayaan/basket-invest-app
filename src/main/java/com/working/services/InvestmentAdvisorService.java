package com.working.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.working.model.InvestmentAdvisor;

public interface InvestmentAdvisorService {
	public ResponseEntity<String> createInvestmentAdvisor(InvestmentAdvisor investmentAdvisor);
	public ResponseEntity<String> updateInvestmentAdvisor(InvestmentAdvisor investmentAdvisor);
	public ResponseEntity<String> deleteInvestmentAdvisor(int iaId);
	public ResponseEntity<List<InvestmentAdvisor>> findAllInvestmentAdvisor();
	public boolean ifExistsInvestmentAdvisor(int iaId);
	public ResponseEntity<List<InvestmentAdvisor>> findInvestmentAdvisor(String iaName);
	public ResponseEntity<List<InvestmentAdvisor>> findByEmailInvestmentAdvisor(String iaEmail);
}
