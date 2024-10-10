package com.kafka.poc.user.service.domain.repository;


import com.kafka.poc.user.service.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
