package com.iamneo.microservices.fundsservice.services.impl;

import com.iamneo.microservices.fundsservice.services.TransactionService;
import com.iamneo.microservices.fundsservice.dto.FundsDto;
import com.iamneo.microservices.fundsservice.entities.Transaction;
import com.iamneo.microservices.fundsservice.mapper.FundsMapper;
import com.iamneo.microservices.fundsservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}