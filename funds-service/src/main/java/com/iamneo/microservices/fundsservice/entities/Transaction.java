package com.iamneo.microservices.fundsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String userId;

    @NonNull
    private String symbol;

    @NonNull
    private String type; // "BUY" or "SELL"
    private double price;

    @NonNull
    private int quantity;
    private Date date;

    // Getters and setters
}
