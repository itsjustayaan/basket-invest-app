package com.working.services.InvestmentAdvisor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.authentication.Roles;
import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.RolesDAO;
import com.working.model.InvestmentAdvisor;

@Service
public class InvestmentAdvisorServiceImpl implements InvestmentAdvisorService{
	

	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;
	
	@Autowired
	RolesDAO rolesDAO;
	
	@Override
	public ResponseEntity<String> createInvestmentAdvisor(InvestmentAdvisor investmentAdvisor) {
		if(investmentAdvisor.getIaName() == "") {
			return new ResponseEntity<>("Investment Advisor Name cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investmentAdvisor.getIaEmail() == "") {
			return new ResponseEntity<>("Investment Advisor Email cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investmentAdvisor.getIaPassword() == "") {
			return new ResponseEntity<>("Investment Advisor Password cannot be Empty",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investmentAdvisorDAO.findById(investmentAdvisor.getIaId()).orElse(null) != null) {
			System.out.println((investmentAdvisorDAO.findById(investmentAdvisor.getIaId())));
			return new ResponseEntity<>("Investment Advisor with this ID Exists",HttpStatus.CONFLICT);
		}
		else {
			investmentAdvisorDAO.save(investmentAdvisor);
			Roles roles = new Roles(investmentAdvisor.getEmail(),investmentAdvisor.getIaPassword(),"ROLE_INVESTMENT_ADVISOR");
			rolesDAO.save(roles);
			return new ResponseEntity<>("Investment Advisor ID Created",HttpStatus.CREATED);
		}
	}

	@Override
	public ResponseEntity<String> updateInvestmentAdvisor(InvestmentAdvisor investmentAdvisor) {
		if(investmentAdvisor.getIaId() <= 0) {
			return new ResponseEntity<>("Investment Advisor ID cannot be negative",HttpStatus.NOT_ACCEPTABLE);
		}
		else if(investmentAdvisorDAO.findById(investmentAdvisor.getIaId()) == null) {
			return new ResponseEntity<>("Investment Advisor with this ID doesn't exist",HttpStatus.CONFLICT);
		}
		else {
			InvestmentAdvisor iaTemp = investmentAdvisorDAO.findById(investmentAdvisor.getIaId()).get();
			if(iaTemp.getIaName().equals(investmentAdvisor.getIaName())) {
				iaTemp.setIaPassword(investmentAdvisor.getIaPassword());
				return new ResponseEntity<>("Investment Advisor with ID Updated",HttpStatus.CREATED);
			}
			else
			return new ResponseEntity<>("Illegal operation: Name Miss match",HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> deleteInvestmentAdvisor(int iaId) {
		if(investmentAdvisorDAO.existsById(iaId)) {
			investmentAdvisorDAO.deleteById(iaId);
			return new ResponseEntity<>("Investor with this ID has been deleted",HttpStatus.OK);
		}
		return new ResponseEntity<>("Investor with this ID doesn't exist",HttpStatus.CONFLICT);
	}

	@Override
	public ResponseEntity<List<InvestmentAdvisor>> findAllInvestmentAdvisor() {
		List<InvestmentAdvisor> allIa= (List<InvestmentAdvisor>) investmentAdvisorDAO.findAll();
		if(allIa.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(allIa,HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public boolean ifExistsInvestmentAdvisor(int iaId) {
		return investmentAdvisorDAO.existsById(iaId);
	}

	@Override
	public ResponseEntity<List<InvestmentAdvisor>> findInvestmentAdvisor(String iaName) {
		return new ResponseEntity<>(investmentAdvisorDAO.findByIaName(iaName),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InvestmentAdvisor>  findByEmailInvestmentAdvisor(String iaEmail) {
		return new ResponseEntity<>(investmentAdvisorDAO.findByEmail(iaEmail).get(0),HttpStatus.OK);
	}

	public InvestmentAdvisor findByEmail(String Email) {
		return investmentAdvisorDAO.findByEmail(Email).get(0);
	}

}
