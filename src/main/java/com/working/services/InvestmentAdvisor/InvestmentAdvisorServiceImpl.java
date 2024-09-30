package com.working.services.InvestmentAdvisor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.UserRepository;
import com.working.model.Users;
import com.working.model.Authority;
import com.working.model.InvestmentAdvisor;

@Service
public class InvestmentAdvisorServiceImpl implements InvestmentAdvisorService{
	

	@Autowired
	InvestmentAdvisorDAO investmentAdvisorDAO;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
    JavaMailSender javaMailSender;
	
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
		    Set<Authority> authorities = new HashSet<>();
		    Authority authority = new Authority(investmentAdvisor.getIaEmail(),"ROLE_INVESTMENT_ADVISOR");
		    authorities.add(authority);
		    Users user = new Users(investmentAdvisor.getIaEmail(), investmentAdvisor.getIaPassword(), true, authorities);
		    System.out.print(investmentAdvisor.getIaEmail() + investmentAdvisor.getIaPassword() + authorities);
		    userRepository.save(user);
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
			Optional<InvestmentAdvisor> iaTemp = investmentAdvisorDAO.findById(investmentAdvisor.getIaId());
			if(iaTemp.get().getIaName().equals(investmentAdvisor.getIaName())) {
				investmentAdvisorDAO.save(investmentAdvisor);
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
	public ResponseEntity<List<InvestmentAdvisor>>  findByEmailInvestmentAdvisor(String iaEmail) {
		return new ResponseEntity<>(investmentAdvisorDAO.findByIaEmail(iaEmail),HttpStatus.OK);
	}
	
	@Override
	 public String generateRandomPassword(int length) {
	        Random random = new Random();
	        StringBuilder password = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int charType = random.nextInt(3);
	            if (charType == 0) { password.append((char) ('0' + random.nextInt(10))); } 
	            else if (charType == 1) { password.append((char) ('A' + random.nextInt(26))); } 
	            else { password.append((char) ('a' + random.nextInt(26))); }
	        }
	        return password.toString();
	 }
	 
	 @Override
	 public void sendEmail(String to, String password) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("New Password for Login");
	        message.setText("Your new password is: " + password +". Use this to login into your investor dashboard");
	        javaMailSender.send(message);
	 }

}
