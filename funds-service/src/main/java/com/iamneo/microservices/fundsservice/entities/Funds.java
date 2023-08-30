package com.iamneo.microservices.fundsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
@AllArgsConstructor
public class Funds {
    @Id
    String userId;
    Double amount;
}
