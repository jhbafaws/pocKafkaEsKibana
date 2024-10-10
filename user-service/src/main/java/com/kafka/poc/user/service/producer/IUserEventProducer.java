package com.kafka.poc.user.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.poc.user.service.infrastructure.dto.UserDto;

public interface IUserEventProducer {

    void sendKafkaUser(UserDto userDto) throws JsonProcessingException;
}
