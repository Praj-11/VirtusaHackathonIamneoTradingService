package com.iamneo.microservices.fundsservice.services;

import com.iamneo.microservices.fundsservice.dto.FundsDto;
import com.iamneo.microservices.fundsservice.entities.Funds;

public interface FundsService {
    FundsDto addFunds(Funds fund);
    FundsDto withdraw(Funds fund);
    Funds getFunds(String userId);
}
