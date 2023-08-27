package com.iamneo.microservices.userservice.services;

import com.iamneo.microservices.userservice.dto.UserDto;
import com.iamneo.microservices.userservice.entities.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(User user);
    UserDto getUserDetailsById(String id);
}
