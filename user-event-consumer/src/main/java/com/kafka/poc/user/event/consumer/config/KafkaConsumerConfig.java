package com.kafka.poc.user.event.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.poc.user.event.consumer.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {


    @Bean
    public Map<String, Object> consumerProps() {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "finytecGroup");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.kafka.poc.user.event.consumer.dto.UserDto"); // Ruta completa de la clase UserDto
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Permitir que cualquier paquete sea deserializado
        props.put("missing-topics-fatal", false);


        return props;

    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }


    @Bean
    public ConsumerFactory<String, UserDto> consumerFactory() {
        // Configuramos el ConsumerFactory para deserializar UserDto
        return new DefaultKafkaConsumerFactory<>(consumerProps(), new StringDeserializer(), new JsonDeserializer<>(UserDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(50);
        factory.setBatchListener(true);
        return factory;

    }


}
