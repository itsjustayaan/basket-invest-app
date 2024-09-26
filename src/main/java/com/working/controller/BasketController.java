package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.model.Basket;
import com.working.services.Basket.BasketServiceImpl;

@RestController
@RequestMapping("basket")
public class BasketController {
	
	@Autowired
	BasketServiceImpl basketImpl;
	
	@PostMapping
	public ResponseEntity<String> setBasket(@RequestBody Basket basket){
		return basketImpl.createBasket(basket);
	}
}
