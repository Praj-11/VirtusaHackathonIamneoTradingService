package com.iamneo.microservices.portfolioservice.model;

import lombok.Data;

@Data
public class StockDto {

    private String symbol;
    private Double price;
    private Integer quantity;
    private Double buyPrice;
    private Double profitPercentage;
}
