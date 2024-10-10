package com.kafka.poc.user.service.domain.service;

import com.kafka.poc.user.service.domain.model.User;

import java.util.Optional;

public interface IUserService {
    User saveUser(User userEntity);

    Optional<User> getByEmail(String email);
}
