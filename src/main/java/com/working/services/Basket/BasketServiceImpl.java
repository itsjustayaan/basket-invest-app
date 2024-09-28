package com.working.services.Basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.dao.BasketDAO;
import com.working.dao.InvestmentAdvisorDAO;
import com.working.model.Basket;

@Service
public class BasketServiceImpl implements BasketService {
	
	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	InvestmentAdvisorDAO iaDAO;

	@Override
	public ResponseEntity<String> createBasket(Basket basket) {
		basketDAO.save(basket);
		return new ResponseEntity<>("Basket Created",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> deleteBasket(int basketId) {
		return null;
	}

}
