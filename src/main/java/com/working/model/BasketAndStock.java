package com.working.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Basket_Stock")
public class BasketAndStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "basketId", nullable = false)
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "stockId", nullable = false)
    private Stock stock;
}
