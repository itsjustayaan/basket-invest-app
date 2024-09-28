package com.working.authentication;

import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Roles {

    @Id
    private String email;

	@Column
    private String password;

    @Column
    private String role;

    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private InvestmentAdvisor investmentAdvisor;

    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Investor investor;
    
    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Admins admin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public InvestmentAdvisor getInvestmentAdvisor() {
        return investmentAdvisor;
    }

    public void setInvestmentAdvisor(InvestmentAdvisor investmentAdvisor) {
        this.investmentAdvisor = investmentAdvisor;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
    
    public Roles(String email, String password, String role) {
  		super();
  		this.email = email;
  		this.password = password;
  		this.role = role;
  	}
}