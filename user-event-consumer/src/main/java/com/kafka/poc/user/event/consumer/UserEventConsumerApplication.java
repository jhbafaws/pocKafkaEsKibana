package com.kafka.poc.user.event.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserEventConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserEventConsumerApplication.class, args);
    }

}
