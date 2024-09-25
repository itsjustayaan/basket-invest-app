package com.working.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.working.model.Basket;

public interface BasketDAO extends JpaRepository<Basket,Integer>{
	public List<Basket> findByBasketName(String basketName);
//	public List<Basket> findByFk_iaId(int iaId);
	
}
