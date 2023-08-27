package com.iamneo.microservices.userservice.controller;

import com.iamneo.microservices.userservice.dto.UserDto;
import com.iamneo.microservices.userservice.entities.User;
import com.iamneo.microservices.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UserService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        return new ResponseEntity<>(UserService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") String userId)
    {
        UserDto apiResponseDto = UserService.getUserDetailsById(userId);
        return new ResponseEntity<UserDto>(apiResponseDto, HttpStatus.CREATED);
    }
}
