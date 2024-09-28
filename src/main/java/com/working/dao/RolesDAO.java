package com.working.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.working.authentication.Roles;
import com.working.model.Investor;

@Repository
public interface RolesDAO extends JpaRepository<Roles,String> {
	
}
