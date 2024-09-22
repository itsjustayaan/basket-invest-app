package com.working.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Investor")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int investorId;

    @Column(name = "investor_name")
    private String investorName;

    @Column(name = "investor_email")
    private String investorEmail;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvestorAndBasket> investorBaskets; // Reference to join table for Investor & Basket

}

