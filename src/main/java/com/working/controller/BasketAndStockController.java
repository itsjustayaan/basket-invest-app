package com.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.model.BasketAndStock;
import com.working.services.BasketAndStock.BasketAndStockServiceImpl;

@RestController
@RequestMapping("basketstock")
public class BasketAndStockController {
	
	@Autowired
	BasketAndStockServiceImpl basketandstockImpl;
	
	@PostMapping
	public ResponseEntity<String> setBasket(@RequestBody BasketAndStock basketstock){
		return basketandstockImpl.createBasket(basketstock);
	}
}
