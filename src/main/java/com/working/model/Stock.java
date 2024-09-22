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
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockId;

    @Column(name = "stock_name")
    private String stockName;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketAndStock> basketStocks;
}

