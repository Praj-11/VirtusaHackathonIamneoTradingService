package com.iamneo.microservices.stockservice.controller;

import com.iamneo.microservices.stockservice.services.StockService;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.SMA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{symbol}")
    public StockQuote fetchStockData(@PathVariable("symbol") String symbol) {
        return stockService.getStockData(symbol);
    }

    @GetMapping("/sma/{symbol}")
    public SMA fetchStocksSMAData(@PathVariable("symbol") String symbol) {
        return stockService.getStockSMAData(symbol);
    }

    @GetMapping("/addFunds")
    public SMA addFunds(@PathVariable("symbol") String symbol) {
        return stockService.getStockSMAData(symbol);
    }

    @PostMapping("/getStockDetails")
    public ResponseEntity<String> fetchStockDataList(@RequestBody List<String> symbolList) {
        return null;
    }
}
