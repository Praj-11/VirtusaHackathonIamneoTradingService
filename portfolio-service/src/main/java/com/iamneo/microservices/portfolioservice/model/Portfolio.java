package com.iamneo.microservices.portfolioservice.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NonNull;

@Entity(name = "portfolio_detail")
@Getter
@Table(name = "portfolio_detail", schema = "portfolioDB")
public class Portfolio {
    
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="stock_symbol")
    @NonNull
    private String stockSymbol;

    @Column(name="quantity")
    @NonNull
    private Integer quantity;

    @Column(name="buy_price")
    @NonNull
    private Double buyPrice;

    public void addQuantity(Integer quantity, Double buyPrice) {
        this .quantity+= quantity;
        this.buyPrice = ((this.buyPrice * this.quantity) + (quantity * buyPrice))/(this.quantity + quantity);
    }

    public void reduceQuantity(Integer quantity){

        this.quantity -= quantity;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
