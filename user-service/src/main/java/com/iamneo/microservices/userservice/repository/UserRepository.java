package com.iamneo.microservices.userservice.repository;

import com.iamneo.microservices.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
