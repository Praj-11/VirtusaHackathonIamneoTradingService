package com.iamneo.microservices.fundsservice.controller;

import com.iamneo.microservices.fundsservice.dto.FundsDto;
import com.iamneo.microservices.fundsservice.services.FundsService;
import com.iamneo.microservices.fundsservice.entities.Funds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funds")
public class FundsController {

    @Autowired
    private FundsService fundsService;

    @PostMapping("/add")
    public ResponseEntity<FundsDto> addFunds(@RequestBody Funds funds){
        return new ResponseEntity<>(fundsService.addFunds(funds), HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<FundsDto> withdrawFunds(@RequestBody Funds funds)
    {
        return new ResponseEntity<>(fundsService.withdraw(funds), HttpStatus.OK);
    }
}
