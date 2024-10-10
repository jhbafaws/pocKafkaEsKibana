package com.kafka.poc.user.service.producer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.poc.user.service.infrastructure.dto.UserDto;
import com.kafka.poc.user.service.producer.IUserEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer implements IUserEventProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired(required = true)
    private ObjectMapper mapper;

    @Override
    public void sendKafkaUser(UserDto userDto) throws JsonProcessingException {

        kafkaTemplate.send("finytec-transactions", userDto.getId(), mapper.writeValueAsString(userDto));

    }
}
