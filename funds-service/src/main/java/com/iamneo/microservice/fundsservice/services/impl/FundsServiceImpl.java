package com.iamneo.microservices.fundsservice.services.impl;

import com.iamneo.microservices.fundsservice.services.FundsService;
import com.iamneo.microservices.fundsservice.dto.FundsDto;
import com.iamneo.microservices.fundsservice.entities.Funds;
import com.iamneo.microservices.fundsservice.mapper.FundsMapper;
import com.iamneo.microservices.fundsservice.repository.FundsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FundsServiceImpl implements FundsService {
    @Autowired
    private FundsRepository fundsRepository;

   /* private final UserDetailsFeignClient userDetailsFeignClient;*/
//    private final FundsMapper fundsMapper;

    @Override
    public FundsDto addFunds(Funds funds) {
       Optional<Funds> data = Optional.ofNullable(fundsRepository.findById(funds.getUserId()).orElse(null));
        log.info("Inside add funds");
        FundsDto amountAdd = null;
       if(data.isEmpty())
       {
           amountAdd = mapToDto(fundsRepository.save(funds));
           return amountAdd;
       }
       else{
           funds.setAmount(data.get().getAmount() + funds.getAmount());
           return mapToDto(fundsRepository.save(funds));
       }

//        return Responce.builder()
//                .fundsDto(fundsMapper.fundsDetails(amountAdd))
//                .build();
//        amountAdd.setUserDetails(userDetailsFeignClient.getUserDetailsById(amountAdd.getUserId()).getBody());
        //return amountAdd;
    }

    public FundsDto withdraw(Funds funds) {
        Long amount = null;
        if (fundsRepository.findById(funds.getUserId()).isPresent()) {
            Optional<Funds> data = fundsRepository.findById(funds.getUserId());
            if(data.get().getAmount()>funds.getAmount())
                amount = data.get().getAmount() - funds.getAmount();
        }
        funds.setAmount(amount);
        return mapToDto(fundsRepository.save(funds));
    }

    private FundsDto mapToDto(Funds funds) {
        return FundsDto.builder()
                .userId(funds.getUserId())
                .amount(funds.getAmount())
                .build();
    }
}
