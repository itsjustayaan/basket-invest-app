package com.working.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.working.model.InvestorAndBasket;

public interface InvestorAndBasketDAO extends JpaRepository<InvestorAndBasket,Integer> {

}
