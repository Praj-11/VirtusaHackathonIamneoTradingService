package com.iamneo.microservices.fundsservice.services.impl;

import com.iamneo.microservices.fundsservice.dto.FundsDto;
import com.iamneo.microservices.fundsservice.dto.StockDto;
import com.iamneo.microservices.fundsservice.entities.Funds;
import com.iamneo.microservices.fundsservice.entities.Transaction;
import com.iamneo.microservices.fundsservice.repository.FundsRepository;
import com.iamneo.microservices.fundsservice.repository.TransactionRepository;
import com.iamneo.microservices.fundsservice.services.FundsService;
import com.iamneo.microservices.fundsservice.services.TransactionService;
import com.iamneo.microservices.fundsservice.services.client.PortfolioFeignClient;
import com.iamneo.microservices.fundsservice.services.client.StocksFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StocksFeignClient stocksClient;

    @Autowired
    private PortfolioFeignClient portfolioClient;

    @Autowired
    private FundsService fundsService;

    public String createTransaction(Transaction transaction) {

        Funds funds = fundsService.getFunds(transaction.getUserId());

        if (funds.getUserId() == null || (funds.getAmount() < (transaction.getPrice() * transaction.getQuantity()))){
            return "Insufficient Funds in User Account";
        }

        StockDto stockDto = stocksClient.getStockDetail(transaction.getSymbol());

        if (stockDto.getPrice() < transaction.getPrice()){
            return "Invalid Price, current market price is: " + stockDto.getPrice();
        }

        if (transaction.getType().equalsIgnoreCase("Buy")){

            stockDto.setQuantity(transaction.getQuantity());
            fundsService.withdraw(new Funds(transaction.getUserId(), transaction.getQuantity() * stockDto.getPrice()));
            portfolioClient.addStockInPortfolio(transaction.getUserId(), stockDto);
        }else {

            stockDto.setQuantity(-transaction.getQuantity());
            portfolioClient.sellStockInPortfolio(transaction.getUserId(), stockDto);
            fundsService.addFunds(new Funds(transaction.getUserId(), transaction.getQuantity() * stockDto.getPrice()));
        }

        transactionRepository.save(transaction);

        return "Trade Successfully Executed";
    }

}