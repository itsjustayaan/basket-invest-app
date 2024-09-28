package com.working.services.BasketAndStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.dao.BasketAndStockDAO;
import com.working.dao.BasketDAO;
import com.working.dao.StockDAO;
import com.working.model.Basket;
import com.working.model.BasketAndStock;
import com.working.model.Stock;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorServiceImpl;

@Service
public class BasketAndStockServiceImpl implements BasketAndStockService {
	
	@Autowired
	BasketAndStockDAO basketStock;
	
	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	StockDAO stockDAO;
	
	@Autowired
	InvestmentAdvisorServiceImpl inv;

	@Override
	public ResponseEntity<String> createBasket(BasketAndStock basketstock) {
		System.out.println(basketstock.getQuantity());
		Stock s1 = stockDAO.findById("INE423A01024").get();
		basketstock.setStock(s1);
		System.out.println(basketstock.getStock().getIndustry());
		Basket b1 = basketDAO.findById(1).get();
		System.out.println(b1);
		basketstock.setBasket(b1);
		basketStock.save(basketstock);
		return new ResponseEntity<>("Basket Created",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> deleteBasket(Basket basket) {
		// TODO Auto-generated method stub
		return null;
	}

}
