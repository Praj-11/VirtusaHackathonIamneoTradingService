package com.iamneo.microservices.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    String userId;
    String userName;
    String phoneNumber;
    String email;
    String panNumber;
    String adharNumber;
    String city;
    String bankAccountNumber;
}
