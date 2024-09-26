package com.working.services.Investor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.dao.InvestorDAO;
import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorService;

@Service
public class InverstorServiceImpl implements InvestorService{
	
	@Autowired
	InvestorDAO investorDAO;
	
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
}
	

