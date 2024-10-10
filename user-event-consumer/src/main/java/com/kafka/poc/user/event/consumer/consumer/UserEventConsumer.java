package com.kafka.poc.user.event.consumer.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.poc.user.event.consumer.dto.UserDto;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);
    @Autowired(required = true)
    private ObjectMapper mapper;

    @Autowired
    private RestHighLevelClient client;

    @KafkaListener(topics = "finytec-transactions", groupId = "finytecGroup")
    public void listen(UserDto userDto) throws JsonProcessingException {
        log.info("Mensaje consumido desde Kafka: {}", userDto); // Agrega un log para asegurar que el mensaje llega
        IndexRequest indexRequest = buildIndexRequest(userDto.getId(), mapper.writeValueAsString(userDto));
        indexRequest.timeout(TimeValue.timeValueMinutes(3));  // Aumentar el timeout si es necesario

        client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("Documento indexado con éxito en ElasticSearch: {}", indexResponse.getId());
            }

            @Override
            public void onFailure(Exception e) {

                log.error("Error al indexar en ElasticSearch: {}", e.getMessage());
            }
        });
    }

    private IndexRequest buildIndexRequest(String key, String value) {
        IndexRequest request = new IndexRequest("finytec-transactions");

        request.id(key); // El id del documento en ElasticSearch
        request.source(value, XContentType.JSON); // El valor en formato JSON
        return request;
    }
}

