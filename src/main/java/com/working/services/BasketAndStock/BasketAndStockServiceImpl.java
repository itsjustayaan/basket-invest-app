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
import com.working.model.InvestmentAdvisor;
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
		InvestmentAdvisor i1 = new InvestmentAdvisor("Ganesh","gg@.com");
		inv.createInvestmentAdvisor(i1);
		System.out.println(i1);
		Basket b1 = new Basket("Test-Basket","Please Work",i1);
		basketDAO.save(b1);
		System.out.println(b1);
		Stock s = new Stock("hjbkjbk","GGWP","Valo","NHK","999.99");
		stockDAO.save(s);
		System.out.println(s);
		basketstock.setBasket(b1);
		basketstock.setStock(s);
		basketstock.setQuantity(5);
		basketStock.save(basketstock);
		return new ResponseEntity<>("Basket Created",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> deleteBasket(Basket basket) {
		// TODO Auto-generated method stub
		return null;
	}

}
