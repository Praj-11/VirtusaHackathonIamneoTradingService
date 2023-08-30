package com.iamneo.microservices.fundsservice.services.impl;

import com.iamneo.microservices.fundsservice.entities.Stock;
import com.iamneo.microservices.fundsservice.repository.StockRepository;
import com.iamneo.microservices.fundsservice.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    // Additional methods if needed
}

