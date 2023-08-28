package com.iamneo.microservices.fundsservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Additional query methods if needed
}