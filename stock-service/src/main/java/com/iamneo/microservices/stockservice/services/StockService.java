package com.iamneo.microservices.stockservice.services;

import io.github.mainstringargs.alphavantagescraper.AlphaVantageConnector;
import io.github.mainstringargs.alphavantagescraper.StockQuotes;
import io.github.mainstringargs.alphavantagescraper.TechnicalIndicators;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.Interval;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.SeriesType;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.TimePeriod;
import io.github.mainstringargs.alphavantagescraper.output.quote.StockQuotesResponse;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.EMA;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.SMA;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.iamneo.microservices.stockservice.model.StockDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    @Value("${stock.key}")
    private String stockApiKey;

    public StockDto getStockData(String symbol){

        StockQuotes stockQuotes = new StockQuotes(getConnector());

        StockQuotesResponse response = stockQuotes.quote(symbol);
        StockQuote stock = response.getStockQuote();
        System.out.printf("Date: %s Price: %s%n", stock.getLatestTradingDay(), stock.getPrice());

        StockDto stockDto = new StockDto(stock.getSymbol(), stock.getPrice(), stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getVolume(), stock.getChange(), stock.getChangePercent());
        return stockDto;
    }

    public SMA getStockSMAData(String symbol, Integer timePeriod) {

        TechnicalIndicators technicalIndicators = new TechnicalIndicators(getConnector());
        SMA smaResponse = technicalIndicators.sma(symbol, Interval.DAILY, TimePeriod.of(timePeriod), SeriesType.OPEN);
        return smaResponse;
    }

    public EMA getStockEMAData(String symbol, Integer timePeriod) {

        TechnicalIndicators technicalIndicators = new TechnicalIndicators(getConnector());
        EMA emaResponse = technicalIndicators.ema(symbol, Interval.DAILY, TimePeriod.of(timePeriod), SeriesType.OPEN);
        return emaResponse;
    }

    @NotNull
    private AlphaVantageConnector getConnector() {

        int timeout = 3000;
        return new AlphaVantageConnector(stockApiKey, timeout);
    }

    public List<StockDto> getStockDataList(List<String> symbolList) {
        StockQuotes stockQuotes = new StockQuotes(getConnector());
        List<StockDto> stockDtoList = new ArrayList<>();

        for (String symbol: symbolList) {
            StockQuotesResponse response = stockQuotes.quote(symbol);
            StockQuote stock = response.getStockQuote();
            System.out.printf("Date: %s Price: %s%n", stock.getLatestTradingDay(), stock.getPrice());

            StockDto stockDto = new StockDto(stock.getSymbol(), stock.getPrice(), stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getVolume(), stock.getChange(), stock.getChangePercent());

            stockDtoList.add(stockDto);
        }
        return stockDtoList;
    }
}