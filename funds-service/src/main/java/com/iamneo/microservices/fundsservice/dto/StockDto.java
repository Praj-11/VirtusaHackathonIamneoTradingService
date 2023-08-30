package com.iamneo.microservices.fundsservice.dto;

import lombok.Data;

@Data
public class StockDto {

    private String symbol;
    private Double price;
    private Integer quantity;
}
