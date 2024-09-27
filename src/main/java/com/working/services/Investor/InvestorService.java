package com.working.services.Investor;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.working.model.Investor;

public interface InvestorService {
	public ResponseEntity<String> createInvestor(Investor investor);
	public ResponseEntity<String> updateInvestor(Investor investor);
	public ResponseEntity<String> deleteInvestor(int investorId);
	public ResponseEntity<List<Investor>> findAllInvestors();
	public boolean ifExistsInvestor(int investorId);
	public ResponseEntity<List<Investor>> findInvestor(String investorName);
	public ResponseEntity<Investor> findByEmailInvestor(String investorEmail);
	public boolean existsByEmail(String email); 
}
