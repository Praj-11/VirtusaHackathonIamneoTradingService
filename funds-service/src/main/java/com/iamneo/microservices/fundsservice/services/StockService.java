package com.iamneo.microservices.fundsservice.services;


import com.iamneo.microservices.fundsservice.entities.Stock;

public interface StockService {
    Stock getStockBySymbol(String symbol);
}