package com.iamneo.microservices.userservice.services;

import com.iamneo.microservices.userservice.dto.LoginDto;
import com.iamneo.microservices.userservice.dto.UserDto;
import com.iamneo.microservices.userservice.entities.User;

public interface UserService {
    UserDto saveUser(User user);
    UserDto getUserDetailsById(String id);
    String loginUser(LoginDto loginDto);
}
