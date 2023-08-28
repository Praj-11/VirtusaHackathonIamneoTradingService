package com.iamneo.microservices.fundsservice.services.impl;

import com.iamneo.microservices.fundsservice.services.StockService;
import com.iamneo.microservices.fundsservice.dto.FundsDto;
import com.iamneo.microservices.fundsservice.entities.Stock;
import com.iamneo.microservices.fundsservice.mapper.FundsMapper;
import com.iamneo.microservices.fundsservice.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

@Service
public class StockService implements StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    // Additional methods if needed
}

