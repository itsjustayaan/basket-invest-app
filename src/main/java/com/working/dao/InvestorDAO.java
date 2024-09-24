package com.working.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.working.model.Investor;

@Repository
public interface InvestorDAO extends JpaRepository<Investor,Integer> {

}
