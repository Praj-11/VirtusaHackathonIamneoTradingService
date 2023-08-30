 package com.iamneo.microservices.fundsservice.services.client;

import com.iamneo.microservices.fundsservice.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

 @FeignClient("stock-service")
 public interface StocksFeignClient {

     @GetMapping("stock/{symbol}")
     StockDto getStockDetail(@PathVariable String symbol);
 }
