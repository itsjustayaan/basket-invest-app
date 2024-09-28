package com.working.services.Admin;

import org.springframework.http.ResponseEntity;

import com.working.model.InvestmentAdvisor;

public interface AdminService {
	public ResponseEntity<InvestmentAdvisor> findByAdmin(String adminEmail);
}
