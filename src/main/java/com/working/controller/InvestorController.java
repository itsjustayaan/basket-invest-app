package com.working.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.dao.BasketDAO;
import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.dao.UserRepository;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;
import com.working.model.Sell;
import com.working.model.Users;
import com.working.services.Investor.InvestorService;

@RestController
@RequestMapping("investor")
public class InvestorController {
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	InvestorDAO investorDAO;
	
	@Autowired
	BasketDAO basketDAO;
	
	@Autowired
	InvestorAndBasketDAO investorAndBasketDAO;
	
	@Autowired
	UserRepository userRepository;
	
		
	@PutMapping("update")
	public ResponseEntity<String> updateInvestor(@RequestBody Investor investor){
		return investorService.updateInvestor(investor);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteInvestor(Principal principal){
		return investorService.deleteInvestor(investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorId());
	}
	
	@PutMapping("updateBalance")
	public ResponseEntity<String> updateBalance(@RequestBody Investor investor){
		return investorService.updateInvestorBalance(investor.getInvestorBalance());
	}
	
	@GetMapping("checkBalance")
	public ResponseEntity<String> getBalance(Principal principal,@RequestBody Investor investor){
		return investorService.getInvestorBalance(principal.getName());
	}
	
	 @PostMapping("sell")
	 public ResponseEntity<String> sellBasket(@RequestBody Sell sell) {
		 Investor investor = investorDAO.findById(sell.getInvestorId()).orElseThrow(() -> new RuntimeException("Investor not found"));
		 Basket basket = basketDAO.findById(sell.getBasketId()).orElseThrow(() -> new RuntimeException("Basket not found"));
		
		 try {
			 investorService.sellBasket(investor, basket, sell.getQuantity());
		     return ResponseEntity.ok("Basket Sold!");
		 } catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Some ERROR Occured");
		     }
	 }
		
	@PostMapping("buy")
	public ResponseEntity<String> setInvestorBasket(@RequestBody InvestorAndBasket investBasket){
		Investor inv = investorDAO.findById(5).get();
		Basket bs = basketDAO.findById(2).get();
		investBasket.setBasket(bs);
		investBasket.setInvestor(inv);
		investorAndBasketDAO.save(investBasket);
		return new ResponseEntity<>("Investor has Basket",HttpStatus.OK);
	}
	
	@GetMapping("viewAr")
	public ResponseEntity<Map<String, Object>> viewAr(Principal principal) {
	    List<InvestorAndBasket> investorAndBasketList = investorDAO.findByInvestorEmail(principal.getName()).get(0).getInvestorAndBasketList();
	    BigDecimal strikePrice = BigDecimal.ZERO;
	    BigDecimal currentPrice = BigDecimal.ZERO;

	    for (InvestorAndBasket investorAndBasket : investorAndBasketList) {
	        strikePrice = strikePrice.add(investorAndBasket.getPriceBought()).multiply(new BigDecimal(investorAndBasket.getQuantity()));
	        currentPrice = currentPrice.add(investorAndBasket.getBasket().calculateBasketPrice()).multiply(new BigDecimal(investorAndBasket.getQuantity()));
	    }

	    BigDecimal absoluteReturn = currentPrice.subtract(strikePrice);
	    BigDecimal percentageReturn = absoluteReturn.divide(currentPrice, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));

	    Map<String, Object> response = new HashMap<>();
	    response.put("absoluteReturn", absoluteReturn);
	    response.put("percentageReturn", percentageReturn);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
 
}
