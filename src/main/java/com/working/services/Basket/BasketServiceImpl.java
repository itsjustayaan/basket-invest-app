package com.working.services.Basket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.dao.BasketDAO;
import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.StockDAO;
import com.working.model.Basket;
import com.working.model.BasketAndStock;
import com.working.model.Stock;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    BasketDAO basketDAO;
    
    @Autowired
    InvestmentAdvisorDAO iaDAO;
    
    @Autowired
    StockDAO stockDAO;

    @Override
    public ResponseEntity<String> createBasket(Basket basket) {
        List<BasketAndStock> basketStockList = basket.getBasketStockList();

        for (BasketAndStock basketStock : basketStockList) {
            Stock stock = stockDAO.findById(basketStock.getStock().getIsin()).orElseThrow(() -> new RuntimeException("Stock with ID " + basketStock.getStock().getStockName() + " not found"));
            basketStock.setStock(stock);
            basketStock.setBasket(basket);
        }

        basketDAO.save(basket);
        return new ResponseEntity<>("Basket and associated stocks created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteBasket(int basketId) {
        // Implement delete logic if necessary
        return new ResponseEntity<>("Delete functionality is not yet implemented", HttpStatus.NOT_IMPLEMENTED);
    }
}
