package com.iamneo.microservices.stockservice.controller;

import com.iamneo.microservice.stockservice.services.StockService;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.EMA;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.SMA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iamneo.microservices.stockservice.model.StockDto;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{symbol}")
    public StockDto fetchStockData(@PathVariable("symbol") String symbol) {
        return stockService.getStockData(symbol);
    }

    @GetMapping("/sma/{symbol}/{time}")
    public SMA fetchStocksSMAData(@PathVariable("symbol") String symbol, @PathVariable("time") Integer time) {
        return stockService.getStockSMAData(symbol, time);
    }

    @GetMapping("/ema/{symbol}/{time}")
    public EMA fetchStocksEMAData(@PathVariable("symbol") String symbol, @PathVariable("time") Integer time) {
        return stockService.getStockEMAData(symbol, time);
    }

    @PostMapping("/getStockDetails")
    public List<StockDto> fetchStockDataList(@RequestBody List<String> symbolList) {
        return stockService.getStockDataList(symbolList);
    }
}
