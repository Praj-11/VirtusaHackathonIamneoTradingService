package com.iamneo.microservices.portfolioservice.services;

import com.iamneo.microservices.portfolioservice.model.Portfolio;
import com.iamneo.microservices.portfolioservice.model.PortfolioResponseDto;
import com.iamneo.microservices.portfolioservice.model.StockDto;
import com.iamneo.microservices.portfolioservice.repository.PortfolioRepository;
import com.iamneo.microservices.portfolioservice.services.client.StocksFeignClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StocksFeignClient stocksFeignClient;

    @Transactional
    public String addStock(Portfolio portfolio){

        Portfolio existingQuantity = portfolioRepository.findByCustomerIdAndStockSymbol(portfolio.getCustomerId(), portfolio.getStockSymbol());

        if (existingQuantity != null){

            existingQuantity.addQuantity(portfolio.getQuantity(), portfolio.getBuyPrice());
            portfolioRepository.save(existingQuantity);
        }else {

            portfolioRepository.save(portfolio);
        }

        return "Success";
    }

    @Transactional
    public String sellStock(Portfolio portfolio){

        Portfolio existingQuantity = portfolioRepository.findByCustomerIdAndStockSymbol(portfolio.getCustomerId(), portfolio.getStockSymbol());

        if (existingQuantity != null){

            if (existingQuantity.getQuantity() < portfolio.getQuantity()){

                return "Invalid Sell Quantity";
            }else if (existingQuantity.getQuantity().equals(portfolio.getQuantity())){

                portfolioRepository.deleteByStockSymbol(existingQuantity.getStockSymbol());
            }else {
                existingQuantity.reduceQuantity(portfolio.getQuantity());
                portfolioRepository.save(existingQuantity);
            }
        }

        return "Success";
    }

    public PortfolioResponseDto getClientPortfolio(Long customerId) {

        List<Portfolio> getStocks = portfolioRepository.findAllByCustomerId(customerId);
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto(customerId);

        if (getStocks.isEmpty()){

            List<String> stocksList = getStocks.stream().map(Portfolio::getStockSymbol).toList();

            Map<String,StockDto> stocksDetails = stocksFeignClient.getStocksDetails(stocksList).stream().
                    collect(Collectors.toMap(StockDto::getSymbol, (i)->i));

            double totalAmount = 0.0;

            for (Portfolio stock : getStocks) {
                stocksDetails.get(stock.getStockSymbol()).setQuantity(stock.getQuantity());
                stocksDetails.get(stock.getStockSymbol()).setBuyPrice(stock.getBuyPrice());
                stocksDetails.get(stock.getStockSymbol()).setProfitPercentage(calculateProfitPercentage(stocksDetails, stock));

                totalAmount += (stock.getQuantity() * stocksDetails.get(stock.getStockSymbol()).getPrice());
                portfolioResponseDto.getStockDto().add(stocksDetails.get(stock.getStockSymbol()));
            }

            portfolioResponseDto.setTotalAmount(totalAmount);
        }

        return portfolioResponseDto;
    }

    private static double calculateProfitPercentage(Map<String, StockDto> stocksDetails, Portfolio stock) {
        return (stocksDetails.get(stock.getStockSymbol()).getPrice() - stock.getBuyPrice()) / stock.getQuantity();
    }
}
