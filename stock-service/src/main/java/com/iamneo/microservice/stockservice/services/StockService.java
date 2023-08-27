package com.iamneo.microservices.stockservice.services;

import io.github.mainstringargs.alphavantagescraper.AlphaVantageConnector;
import io.github.mainstringargs.alphavantagescraper.StockQuotes;
import io.github.mainstringargs.alphavantagescraper.TechnicalIndicators;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.Interval;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.SeriesType;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.TimePeriod;
import io.github.mainstringargs.alphavantagescraper.output.quote.StockQuotesResponse;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.SMA;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Value("${stock.key}")
    private String stockApiKey;

    public StockQuote getStockData(String symbol){

        StockQuotes stockQuotes = new StockQuotes(getConnector());

        StockQuotesResponse response = stockQuotes.quote(symbol);
        StockQuote stock = response.getStockQuote();
        System.out.printf("Date: %s Price: %s%n", stock.getLatestTradingDay(), stock.getPrice());

        return stock;
    }

    public SMA getStockSMAData(String symbol, Integer timePeriod) {

        TechnicalIndicators technicalIndicators = new TechnicalIndicators(getConnector());
        SMA smaResponse = technicalIndicators.sma(symbol, Interval.DAILY, TimePeriod.of(timePeriod), SeriesType.OPEN);
        return smaResponse;
    }

    @NotNull
    private AlphaVantageConnector getConnector() {

        int timeout = 3000;
        return new AlphaVantageConnector(stockApiKey, timeout);
    }

}