package com.iamneo.microservices.fundsservice.services;

import com.iamneo.microservices.fundsservice.entities.Transaction;

public interface TransactionService {
    String createTransaction(Transaction transaction);
}