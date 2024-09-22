package com.working.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Basket")
public class Basket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketId;

    @ManyToOne
    @JoinColumn(name = "adminId", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "iaId", nullable = false)
    private InvestmentAdvisor investmentAdvisor;

    @Column(name = "basket_name")
    private String basketName;

    @Column(name = "basket_summary")
    private String basketSummary;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketAndStock> basketStocks;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvestorAndBasket> investorBaskets;
}
