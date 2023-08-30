package com.iamneo.microservices.fundsservice.repository;

import com.iamneo.microservices.fundsservice.entities.Funds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundsRepository extends JpaRepository<Funds, String> {

}
