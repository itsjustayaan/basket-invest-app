package com.working.services.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.working.authentication.Admins;
import com.working.dao.AdminDAO;
import com.working.dao.InvestorDAO;
import com.working.model.InvestmentAdvisor;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO adminDAO;

	@Override
	public ResponseEntity findByAdmin(String adminEmail) {
		return new ResponseEntity<>(adminDAO.findByEmail(adminEmail).get(0),HttpStatus.OK);
	}

}
