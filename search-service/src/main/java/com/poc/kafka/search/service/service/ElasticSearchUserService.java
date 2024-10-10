package com.poc.kafka.search.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poc.kafka.search.service.dto.UserDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticSearchUserService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    // Método para obtener un usuario por ID desde ElasticSearch
    public UserDto getUserById(String id) throws IOException {
        GetRequest getRequest = new GetRequest("finytec-transactions", id);  // Cambia el nombre del índice si es necesario
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        if (getResponse.isExists()) {
            return objectMapper.readValue(getResponse.getSourceAsString(), UserDto.class);
        } else {
            return null;  // O puedes lanzar una excepción personalizada
        }
    }

    // Método para buscar usuarios en ElasticSearch
    public List<UserDto> searchUsersByName(String name) throws IOException {
        SearchRequest searchRequest = new SearchRequest("finytec-transactions"); // Cambia el índice si es necesario
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name", name));  // Buscar por nombre
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<UserDto> users = new ArrayList<>();

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            users.add(objectMapper.readValue(hit.getSourceAsString(), UserDto.class));
        }

        return users;
    }

    public List<UserDto> searchAllUsers() throws IOException {
        SearchRequest searchRequest = new SearchRequest("finytec-transactions"); // Cambia el índice si es necesario
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());  // Buscar por nombre

        // Configura el tamaño máximo de resultados a devolver (por ejemplo, 1000)
        sourceBuilder.size(1000);  // Ajusta según el número de documentos que esperas
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<UserDto> users = new ArrayList<>();

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            users.add(objectMapper.readValue(hit.getSourceAsString(), UserDto.class));
        }

        return users;
    }
}
