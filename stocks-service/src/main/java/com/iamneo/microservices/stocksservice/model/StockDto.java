package com.iamneo.microservices.stocksservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockDto {

    private String symbol;
    private Double price;
    private final double open;
    private final double high;
    private final double low;
    private final long volume;
    private final double change;
    private final double changePercent;

    public StockDto(String symbol, Double price, double open, double high, double low, long volume, double change, double changePercent) {
        this.symbol = symbol;
        this.price = price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.change = change;
        this.changePercent = changePercent;
    }
}
