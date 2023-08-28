package com.iamneo.microservices.portfolioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iamneo.microservices.portfolioservice.model.Portfolio;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

    List<Portfolio> findAllByCustomerId(Long customerId);

    Portfolio findByCustomerIdAndStockSymbol(Long customerId, String stockSymbol);

    void deleteByStockSymbol(String stockSymbol);
}
