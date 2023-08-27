package com.iamneo.microservices.userservice.services.impl;

import com.iamneo.microservices.userservice.dto.LoginDto;
import com.iamneo.microservices.userservice.dto.UserDto;
import com.iamneo.microservices.userservice.entities.User;
import com.iamneo.microservices.userservice.exception.GlobalExceptionHandler;
import com.iamneo.microservices.userservice.repository.UserRepository;
import com.iamneo.microservices.userservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public UserDto saveUser(User user) {
        log.info("Inside create user");
        user.setUserId(UUID.randomUUID().toString());
        return mapToDto(userRepo.save(user));
    }

    @Override
    //@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultPolicySelection")
    public UserDto getUserDetailsById(String userId) {
        log.info("Inside getUserDetailsById");
        User user = userRepo.findById(userId).orElseThrow(() -> new GlobalExceptionHandler(
                String.format("User Not found with the Id : %s", userId)
        ));
        return mapToDto(user);
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        User loginUser = null;
        if (userRepo.findById(loginDto.getUserId()).isPresent()){

            loginUser = userRepo.findById(loginDto.getUserId()).get();
        }

        if (loginUser != null && loginDto.getPassword().equalsIgnoreCase(loginUser.getPassword())) {

            return getUserWelcomeDetails(loginUser);
        }else {

            return "Invalid Details";
        }
    }

    private String getUserWelcomeDetails(User loginUser) {

        String response;
        switch (loginUser.getRole()){
            case "InvestmentProf": response = ""; break;
            case "Traders": response = ""; break;
            case "RiskManagers": response = ""; break;
            default:  response = ""; break;
        }

        return response;
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .city(user.getCity())
                .panNumber(user.getPanNumber())
                .adharNumber(user.getAdharNumber())
                .bankAccountNumber(user.getBankAccountNumber())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }
}
