package com.poc.kafka.search.service.controller;

import com.poc.kafka.search.service.dto.UserDto;
import com.poc.kafka.search.service.service.ElasticSearchUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ElasticSearchUserService userService;

    // Endpoint para obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) throws IOException {

    log.info("getUserById, PathVariable : {} ", id);
        UserDto user = userService.getUserById(id);
        log.info("getUserById, getUserByid for elasticSearch : {} ", user);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            log.error("getUserById, getUserByid  NOT found for elasticSearch : {} ", id);
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para buscar usuarios por su nombre
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsersByName(@RequestParam String name) throws IOException {
        List<UserDto> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> searchAll() throws IOException {

        List<UserDto> userDtoList = userService.searchAllUsers();

        return  ResponseEntity.ok(userDtoList);
    }
}
