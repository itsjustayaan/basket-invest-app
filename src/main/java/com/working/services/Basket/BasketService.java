package com.working.services.Basket;

import org.springframework.http.ResponseEntity;

import com.working.model.Basket;

public interface BasketService {
	public ResponseEntity<String> createBasket(Basket basket);
	public ResponseEntity<String> deleteBasket(int basketId);
}
