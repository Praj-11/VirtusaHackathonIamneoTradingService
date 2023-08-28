package com.iamneo.microservices.fundsservice.services;

import com.iamneo.microservices.fundsservice.entities.Funds;


public interface StockService {
    Stock getStockBySymbol(String symbol);
}