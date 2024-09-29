package com.working.services.Investor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.dao.InvestorAndBasketDAO;
import com.working.dao.InvestorDAO;
import com.working.dao.UserRepository;
import com.working.model.Authority;
import com.working.model.Basket;
import com.working.model.Investor;
import com.working.model.InvestorAndBasket;
import com.working.model.Users;

import jakarta.transaction.Transactional;

@Service
public class InverstorServiceImpl implements InvestorService{
	
	@Autowired
	InvestorDAO investorDAO;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InvestorAndBasketDAO investorAndBasketRepository;
	
	@Override
	public ResponseEntity<String> createInvestor(Investor investor){
		if(investor.getInvestorName() == "") {
			return new ResponseEntity<>("Investor Name cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investor.getInvestorEmail() == "") {
			return new ResponseEntity<>("Investor Email cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investor.getInvestorPassword() == "") {
			return new ResponseEntity<>("Investor Password cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investorDAO.findById(investor.getInvestorId()).orElse(null) != null) {
			System.out.println((investorDAO.findById(investor.getInvestorId())));
			return new ResponseEntity<>("Investorwith this ID Exists",HttpStatus.CONFLICT);
		}
		else {
			investorDAO.save(investor);
		    Set<Authority> authorities = new HashSet<>();
		    Authority authority = new Authority(investor.getInvestorEmail(),"INVESTOR");
		    authorities.add(authority);
		    Users user = new Users(investor.getInvestorEmail(), investor.getInvestorPassword(), true, authorities);
		    userRepository.save(user);
			return new ResponseEntity<>("Investor ID Created",HttpStatus.CREATED);
		}
	}
	
	@Override
	public ResponseEntity<String> updateInvestor(Investor investor){
		if(investor.getInvestorId() <= 0) {
			return new ResponseEntity<>("InvestorID cannot be negative",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investorDAO.findById(investor.getInvestorId()) == null) {
			return new ResponseEntity<>("Investor with this ID doesn't exist",HttpStatus.CONFLICT);
		}
		else {
			Optional<Investor> investorTemp = investorDAO.findById(investor.getInvestorId());
			if(investorTemp.get().getInvestorName().equals(investor.getInvestorName())) {
				investorDAO.save(investor);
				return new ResponseEntity<>("Investor with ID Updated",HttpStatus.CREATED);
			}
			else
			return new ResponseEntity<>("Illegal operation: Name Miss match",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@Override
	public ResponseEntity<String> deleteInvestor(int investorId){
		if(investorDAO.existsById(investorId)) {
			investorDAO.deleteById(investorId);
			return new ResponseEntity<>("Investor with this ID has been deleted",HttpStatus.OK);
		}
		return new ResponseEntity<>("Investor with this ID doesn't exist",HttpStatus.CONFLICT);
	}
	
	@Override
	public ResponseEntity<List<Investor>> findAllInvestors(){
		List<Investor> allIa= (List<Investor>) investorDAO.findAll();
		if(allIa.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(allIa,HttpStatus.NOT_ACCEPTABLE);
	}
	
	public boolean ifExistsInvestor(int investorId) {
		return investorDAO.existsById(investorId);
	}
	
	public ResponseEntity<List<Investor>> findInvestor(String investorName){
		return new ResponseEntity<>(investorDAO.findByInvestorName(investorName),HttpStatus.OK);
	}
	
	public ResponseEntity<List<Investor>> findByEmailInvestor(String investorEmail){
		return new ResponseEntity<>(investorDAO.findByInvestorEmail(investorEmail),HttpStatus.OK);
	}
	
	public boolean existsByEmail(String email) {
		return investorDAO.existsByInvestorEmail(email);
	}

	@Override
	public ResponseEntity<String> updateInvestorBalance(int balance) {
		System.out.print(balance);
		Investor inv = new Investor("Jay","gg@gmail.com","lessgoo",10);
		investorDAO.save(inv);
		inv.setInvestorBalance(balance);
		investorDAO.save(inv);
		return null;
	}
	
	 @Transactional
	    public void sellBasket(Investor investor, Basket basket, int quantityToSell) throws Exception {

	        // Check if the investor owns the basket
	        InvestorAndBasket investorAndBasket = investorAndBasketRepository.findByInvestorAndBasket(investor, basket);
	        
	        if (investorAndBasket == null) {
	            throw new Exception("You don't own this basket. Short selling is not allowed.");
	        }

	        // Check if the quantity to sell is valid (no fractional selling)
	        if (quantityToSell <= 0 || quantityToSell > investorAndBasket.getQuantity()) {
	            throw new Exception("Invalid quantity. You can only sell whole baskets you own.");
	        }

	        // Calculate the price of the basket
	        BigDecimal basketPrice = basket.calculateBasketPrice();

	        // Update the investor's balance
	        BigDecimal amountReceived = basketPrice.multiply(new BigDecimal(quantityToSell));
	        investor.setInvestorBalance(investor.getInvestorBalance() + amountReceived.intValue());

	        // Update the investor's basket quantity
	        int remainingQuantity = investorAndBasket.getQuantity() - quantityToSell;
	        if (remainingQuantity == 0) {
	            // Remove the basket if the investor has sold all of their shares
	            investorAndBasketRepository.delete(investorAndBasket);
	        } else {
	            investorAndBasket.setQuantity(remainingQuantity);
	            investorAndBasketRepository.save(investorAndBasket);
	        }

	        // Save the updated investor data
	        investorDAO.save(investor);
	    }
}
	

