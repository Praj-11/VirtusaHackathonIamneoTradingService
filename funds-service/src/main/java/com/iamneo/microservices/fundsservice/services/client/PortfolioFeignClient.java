 package com.iamneo.microservices.fundsservice.services.client;

import com.iamneo.microservices.fundsservice.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @FeignClient("portfolio-service")
 public interface PortfolioFeignClient {

     @PostMapping("portfolio/{userId}/add_stock")
     String addStockInPortfolio(@PathVariable String userId, @RequestBody StockDto stockDetail);

     @PostMapping("portfolio/{userId}/add_stock")
     String sellStockInPortfolio(@PathVariable String userId, @RequestBody StockDto stockDetail);
 }
