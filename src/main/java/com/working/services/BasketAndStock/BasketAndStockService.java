package com.working.services.BasketAndStock;

import org.springframework.http.ResponseEntity;

import com.working.model.Basket;
import com.working.model.BasketAndStock;

public interface BasketAndStockService {
	public ResponseEntity<String> createBasket(BasketAndStock basketstock);
	public ResponseEntity<String> deleteBasket(Basket basket);
}
