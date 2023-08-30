package com.iamneo.microservices.userservice.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginDto {

    String userId;
    String password;
}
