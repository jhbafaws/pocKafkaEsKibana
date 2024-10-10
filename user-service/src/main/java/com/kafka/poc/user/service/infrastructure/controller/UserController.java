package com.kafka.poc.user.service.infrastructure.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.poc.user.service.domain.model.User;
import com.kafka.poc.user.service.infrastructure.dto.UserDto;
import com.kafka.poc.user.service.infrastructure.exception.BadRequestException;
import com.kafka.poc.user.service.infrastructure.usecase.IUserCase;
import com.kafka.poc.user.service.producer.IUserEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    IUserCase iUserCase;

    @Autowired
    IUserEventProducer iUserEventProducer;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws JsonProcessingException {


        UserDto response = iUserCase.createUser(userDto);

        iUserEventProducer.sendKafkaUser(response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
