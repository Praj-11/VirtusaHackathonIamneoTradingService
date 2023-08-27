package com.iamneo.microservices.fundsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundsDto {
    String userId;
    Long amount;
    //UserDto userDetails;
}
