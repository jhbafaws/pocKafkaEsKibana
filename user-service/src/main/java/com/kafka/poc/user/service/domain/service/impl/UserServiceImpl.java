package com.kafka.poc.user.service.domain.service.impl;


import com.kafka.poc.user.service.domain.model.User;
import com.kafka.poc.user.service.domain.repository.UserRepository;
import com.kafka.poc.user.service.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<User> getByEmail(String email) {

        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
