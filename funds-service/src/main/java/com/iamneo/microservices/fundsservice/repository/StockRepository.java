package com.iamneo.microservices.fundsservice.repository;
import com.iamneo.microservices.fundsservice.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findBySymbol(String symbol);
}