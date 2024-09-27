package com.working.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.working.model.Investor;

@Repository
public interface InvestorDAO extends JpaRepository<Investor,Integer> {
	public List<Investor> findByEmail(String investorEmail);
	public List<Investor> findByInvestorName(String investorName);
	public boolean existsByEmail(String email);

}