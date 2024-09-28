package com.working.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.working.model.Investor;
import com.working.authentication.Admins;

@Repository
public interface AdminDAO extends JpaRepository<Admins, Integer>{
	public List<Admins> findByEmail(String adminEmail);
}
