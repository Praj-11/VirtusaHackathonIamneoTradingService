package com.iamneo.microservices.fundsservice.services;

import com.iamneo.microservices.fundsservice.entities.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);